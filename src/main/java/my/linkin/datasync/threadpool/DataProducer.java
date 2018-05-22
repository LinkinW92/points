package my.linkin.datasync.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.linkin.spring.holder.SpringContextHolder;

import java.util.List;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 上午10:12 18-4-26
 */
public abstract class DataProducer<T> implements Runnable{

    private Logger logger = LoggerFactory.getLogger(DataProducer.class);

    protected DataQueue queue;

    protected Split split;

    protected String name;

    private SyncMonitorService service;

    public DataProducer  build(DataQueue queue) {
        this.queue = queue;
        return this;
    }

    public DataProducer(Split split, String name) {
        this.split = split;
        this.name = name;
        service = SpringContextHolder.getBean(SyncMonitorService.class);
    }

    @Override
    public void run() {
        produce();
    }

    public abstract List<T> readData(Split split);

    public void produce() {
        service.addProducer();
        List<T> list = readData(split);
        try {
            for(T obj : list)
                queue.put(obj);
        } catch (InterruptedException e) {
            //TODO 中断请求
            Thread.currentThread().interrupt();
        } finally {
            service.decProducer();
            logger.info("数据生产完毕，关闭生产者， 当前生产者个数:{}", service.getProducer());
        }
    }
}
