package ThreadTest.chapter5;

import ThreadTest.chapter4.test.SleepUtils;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * Created by YangDeming on 2018/1/11.
 */
public class TwinsLockTest {
    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动 10 个 线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);//守护线程
            w.start();
        }
        // 每隔 1 秒 换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}


