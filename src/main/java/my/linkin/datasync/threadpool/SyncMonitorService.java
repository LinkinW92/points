package my.linkin.datasync.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 下午4:05 18-5-4
 */
@Component
public class SyncMonitorService {

    private Logger logger = LoggerFactory.getLogger(SyncMonitorService.class);

    private DataProcessCenter center;

    private MonitorInfo info;

    private synchronized void init() {
        if(null == info) {
            info = new MonitorInfo();
        }
    }

    public void monitor(DataProcessCenter center) {
        init();
        this.center = center;
    }

    public void tryToStop() {

        if(info.getConsumers() == 0 && this.center.isCenterEmpty()) {
            logger.info("接收到停止服务请求， 3秒后尝试关闭服务...{}", info.getConsumers());
            try{
                Thread.sleep(3000);
            }catch (Exception e) {
                logger.error("等待服务关闭出现异常， 继续尝试关闭服务...");
            }finally {
                if(info.getConsumers() == 0 && this.center.isCenterEmpty())
                    center.stop();
            }
        }
    }

    public void addConsumer() {
        info.addConsumer();
    }

    public void decConsumer() {
        info.decConsumer();
    }

    public int getConsumer() {
        return info.getConsumers();
    }

    public void addProducer() {
        info.addProducer();
    }

    public void decProducer() {
        info.decProducer();
    }

    public int getProducer() {return info.getProducers();}

    public void getException() {
        info.getException();
    }

    public void addException() {
        info.addException();
    }
}
