package ThreadTest.chapter5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by YangDeming on 2018/1/10.
 * Lock接口的实现基本上都是通过聚合了一个同步器的子类来完成线程控制的
 */
public class LockUseCase {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        //不要在try中获取锁，不然在出现异常以后就会在抛出异常的同时释放掉
        lock.lock();//获取锁
        lock.lockInterruptibly();//可中断获取锁
        lock.tryLock();//尝试非阻塞的获取锁
        lock.newCondition();//获取等待通知组件，该组件和当前线程绑定
        try {
        } finally {
            //保证最终能被释放
            lock.unlock();
        }
    }


}
