package my.linkin.datasync.threadpool;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 下午1:44 18-4-27
 */
@Component
public class ProcessorFactory {

    private Map<Class<?>, Processor> fatory = Maps.newHashMap();

    public Processor getProcessor(Class<?> clz) {
        return fatory.get(clz);
    }

    public void register(Class<?> clz, Processor processor) {
        fatory.put(clz, processor);
    }
}
