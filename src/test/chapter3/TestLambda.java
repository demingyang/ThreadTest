package test.chapter3;


import java.util.Date;

/**
 * Created by YangDeming on 2017/12/19.
 */
public class TestLambda {

    public static void main(String[] args) {
        Long a = System.currentTimeMillis();
        Long b = a - 1000 * 60 * 31;
        Date date = new Date(b);
        System.out.println(date);
        Boolean aBoolean = checkClosedAccount(date);
        System.out.println(aBoolean);
    }

    static Boolean checkClosedAccount(Date closedAccountTime) {
        if (closedAccountTime != null) {
            Long close = closedAccountTime.getTime();
            Long now = System.currentTimeMillis();
            System.out.println(close - now);
            if ((close - now) <= 1000 * 60 * 30 && (close - now) > 0) {
                return true;
            }
        }
        return false;
    }
}
