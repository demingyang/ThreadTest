package ThreadTest.chapter10;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by YangDeming on 2018/1/17.
 */
public class H {
    public static void main(String[] args) {
        String s = "消费说明：加友成功系统扣费，产品名称：003-留学，成功数量：1,产品价格6";
        Map map = new HashMap<>();
        map.put("",s);
        Set set = map.entrySet();
        for(Iterator i = set.iterator(); i.hasNext();){
            Object next = i.next();
            if(next instanceof  String[]){
                System.out.println("++++++++++++++++++++++");
            }else {
                System.out.println("-------------------");
            }
        }

    }

    public static Date getLastday() {
        Calendar cale = null;
        cale = Calendar.getInstance();
        System.out.println(cale.getTime());
        cale.add(2, 1);
        cale.set(5, 0);
        return cale.getTime();
    }
}
