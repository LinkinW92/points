package my.linkin.datasync.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.linkin.spring.holder.SpringContextHolder;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 上午10:11 18-4-26
 */
public class DataProcessCenter {


    private static Logger logger = LoggerFactory.getLogger(DataProcessCenter.class);

    private DataQueue queue = new DataQueue("结算订单数据同步队列", 1000);

    private List<DataProducer> producers;

    private SyncThreadPoolExecutor executor;


    private final int numberOfConsumer = 2;

    private final int corePoolSize = 5;
    private final int maximumPoolSize = 32;
    private final long keepAliveTime = 1000L;
    private TimeUnit unit = TimeUnit.SECONDS;

    /**
     * 线程池满的情况下， 采用调用者运行策略：CallerRunsPolicy
     * */
    public DataProcessCenter(List<DataProducer> producers) {
        this.producers = producers;
        this.executor = new SyncThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                new SynchronousQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    }

    private void init() {
        this.executor = new SyncThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                new SynchronousQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public void process() {
        if(executor == null) init();
        //开始监控
        SyncMonitorService monitor = SpringContextHolder.getBean(SyncMonitorService.class);
        monitor.monitor(this);

        //TODO 当生产者过多时， 实现提交策略
        producers.forEach(producer -> {
            producer.build(queue);
        });
        //开启生产者消费者， 生产者消费者都是用SyncThread包装提交到线程池的。
        producers.forEach(producer -> executor.submit(new SyncThread(producer)));

        //消费者线程名为C_序号
        for(int i = 0; i< numberOfConsumer; i++) {
            executor.submit(new SyncThread(new DataConsumer().build(queue, "C_" + i)));
        }

    }

    public void stop() {
        executor.shutdown();
        logger.info("服务关闭完成...");
    }

    private void awaitForTermination() {

    }

    public Future<?> submit(Runnable r) {
        if(null == executor) {
            init();
        }
        return executor.submit(r);
    }

    public boolean isCenterEmpty() {
        return queue.isEmpty();
    }

}
