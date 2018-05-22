package my.linkin.datasync.threadpool;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: chunhui.wu
 * @Desc:  任务执行的监控信息
 * @Date: Created in 下午5:17 18-5-3
 */
public class MonitorInfo {

    /**
     * 线程存活数
     * */
    private AtomicInteger alive = new AtomicInteger();

    /**
     *线程创建数
     * */
    private AtomicInteger created = new AtomicInteger();

    /**
     *线程异常数
     * */
    private AtomicInteger exception = new AtomicInteger();

    /**
     *总消费的数据量
     * */
    private AtomicInteger consumed = new AtomicInteger();

    /**
     *总生产的数据量
     * */
    private AtomicInteger produced = new AtomicInteger();

    /**
     *总异常元素数量
     * */
    private AtomicInteger errElems = new AtomicInteger();

    /**
     * 消费者数量
     * */
    private AtomicInteger consumers = new AtomicInteger();

    /**
     * 生产者数量
     * */
    private AtomicInteger producers = new AtomicInteger();

    /**
     * 获取每个线程的执行时间
     * */
    private Map<String, Long> getRuntime4Thread() {
        return null;
    }

    public int getAlive() {
        return alive.get();
    }

    public void setAlive(int num) {
        alive.set(num);
    }

    public int getCreated() {
        return created.get();
    }

    public void setCreated(int num) {
        this.created.set(num);
    }

    public int getException() {
        return exception.get();
    }

    public void addException() {
        this.exception.incrementAndGet();
    }

    public int getConsumed() {
        return consumed.get();
    }

    public void setConsumed(int num) {
        this.consumed.set(num);
    }

    public int getProduced() {
        return produced.get();
    }

    public void setProduced(int num) {
        this.produced.set(num);
    }

    public int getErrElems() {
        return errElems.get();
    }

    public void setErrElems(int num) {
        this.errElems.set(num);
    }

    public int getConsumers() {
        return consumers.get();
    }

    public void addConsumer() {
        this.consumers.incrementAndGet();
    }

    public void decConsumer() {
        this.consumers.decrementAndGet();
    }

    public int getProducers() {
        return producers.get();
    }

    public void addProducer() {
        this.producers.incrementAndGet();
    }

    public void decProducer() {
        this.producers.decrementAndGet();
    }

}
