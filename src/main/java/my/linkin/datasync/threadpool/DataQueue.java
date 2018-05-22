package my.linkin.datasync.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: chunhui.wu
 * @Desc:
 * @Date: Created in 上午10:18 18-4-26
 */
public class DataQueue<T> extends  LinkedBlockingQueue<T>{

    private String name;

    private int size;


    public DataQueue(String name, int size) {
        super(size);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
