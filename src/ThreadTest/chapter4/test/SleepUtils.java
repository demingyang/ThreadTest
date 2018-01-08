package ThreadTest.chapter4.test;

import java.util.concurrent.TimeUnit;

/**
 * Created by YangDeming on 2018/1/5.
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

}
