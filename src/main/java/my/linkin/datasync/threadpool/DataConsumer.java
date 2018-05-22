package my.linkin.datasync.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

import my.linkin.spring.holder.SpringContextHolder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 上午10:12 18-4-26
 */
public class DataConsumer implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(DataConsumer.class);

    private static final int TIMEOUT = 5;



    private DataQueue queue;

    private String name;

    private ProcessorFactory factory;

    private SyncMonitorService service;

    //维持一个异常元素的列表， 对消费失败的元素进行二次处理.
    List<Object> errorElems = Lists.newArrayList();


    public DataConsumer build(DataQueue queue, String name) {
        this.queue = queue;
        this.name = name;
        factory = SpringContextHolder.getBean(ProcessorFactory.class);
        service = SpringContextHolder.getBean(SyncMonitorService.class);
        return this;
    }

    @Override
    public void run() {
        consume();
    }

    private void consume() {
        //消费者未消费到数据后的重试消费的次数
        final AtomicInteger RETRY = new AtomicInteger(2);
        Object obj;
        service.addConsumer();
        logger.info("当前消费者数：{}, 尝试次数：{}", service.getConsumer(), RETRY);

        while(RETRY.get() > 0) {
            try{
                obj = queue.poll(TIMEOUT, TimeUnit.SECONDS);
                if(null != obj) {
                    try{
                        factory.getProcessor(obj.getClass()).process(obj);
                    }catch (Exception e) {
                        //异常时，记录异常元素即可.
                        errorElems.add(obj);
                    }
                }else {
                    RETRY.decrementAndGet();
                    logger.info("【结算同步】{} 秒内消费者未消费数据， 重试【{}】次后将关闭", TIMEOUT, RETRY.get());
                }

            }catch (InterruptedException i) {
                //TODO 中断请求 暂时恢复中断
                Thread.currentThread().interrupt();
            }
        }

        //执行线程关闭的清理工作
        errorElems.forEach(x -> Hook.hook(x));

        service.decConsumer();
        logger.info("当前消费者数量{}", service.getConsumer());
        service.tryToStop();

    }

    static class Hook {
        public static void hook(Object obj) {
            //暂时打印日志
            logger.warn("【结算订单】异常元素：{}", obj.toString());
        }
    }
}
