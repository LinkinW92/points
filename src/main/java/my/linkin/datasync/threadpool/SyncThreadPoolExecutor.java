package my.linkin.datasync.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 上午10:40 18-4-27
 */
public class SyncThreadPoolExecutor extends ThreadPoolExecutor {


    private static Logger logger = LoggerFactory.getLogger(SyncThreadPoolExecutor.class);


    public SyncThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public SyncThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public SyncThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public SyncThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void afterExecute(Runnable r, Throwable t) {
        if(r instanceof DataConsumer) {
            logger.info("这是一个消费者");
        }
        if(r instanceof DataProducer) {
            logger.info("这是一个生产者");
        }

        if(r instanceof SyncThread) {
            logger.info("这是一个线程");
        }
    }

    @Override
    public void beforeExecute(Thread t, Runnable r) {

    }

    @Override
    public void terminated() {

    }
}
