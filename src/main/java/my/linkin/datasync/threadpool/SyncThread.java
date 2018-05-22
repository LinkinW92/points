package my.linkin.datasync.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.linkin.spring.holder.SpringContextHolder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 上午9:52 18-4-27
 */
public class SyncThread extends Thread{

    private Logger logger = LoggerFactory.getLogger(SyncThread.class);


    //线程创建数
    private static final AtomicInteger created = new AtomicInteger();
    //线程异常数
    private static final AtomicInteger exception = new AtomicInteger();
    //线程名称
    private static final String DEFAULT_NAME = "SyncThread";

    private SyncMonitorService service;

    public SyncThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public SyncThread(Runnable r, String name) {
        super(r, name + "-" + created.getAndIncrement());

        //未捕获的异常打印日志
        setDefaultUncaughtExceptionHandler((t, e) -> {
            exception.incrementAndGet();
            logger.warn("线程{} 发生未捕获异常：{}", t.getName(), e);
        });

        service = SpringContextHolder.getBean(SyncMonitorService.class);
    }

    public void run() {
        try {
            super.run();
        }catch(Exception e) {
            logger.warn("线程{} 发生异常：{}", this.getName(), e.getMessage());
            service.addException();
        }finally {
            //更新监控信息

        }
    }


    public int getCreated() {
        return created.get();
    }

    public int getException() {
        return exception.get();
    }
}
