package ThreadTest.chapter4.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by YangDeming on 2018/1/8.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        public void run() {
            // 加锁， 拥有 lock 的 Monitor
            synchronized (lock) {
                // 当 条件 不满足 时， 继续 wait， 同时 释放 了 lock 的 锁 while (flag)
                {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat(" HH: mm: ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                // 条件 满足 时， 完成 工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat(" HH: mm: ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            // 加锁， 拥有 lock 的 Monitor
            synchronized (lock) {
                // 获取 lock 的 锁， 然后 进行 通知， 通知 时不 会 释放 lock 的 锁，
                // 直到 当前 线程 释放 了 lock 后， WaitThread 才能 从 wait 方法 中 返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat(" HH: mm: ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            // 再次 加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat(" HH: mm: ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}

