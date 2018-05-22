package my.linkin.datasync.threadpool;

import java.util.Date;


/**
 * @Author: chunhui.wu
 * @Desc: 时间切片  数据查询区域的基本单位。 在订单表中， 是对create_date的切片， 客票表中是对建单时间（order_create_date）的切片
 * @Date: Created in 下午2:20 18-4-26
 */
public class Split {

    private Date start;

    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
