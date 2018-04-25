package ThreadTest.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by YangDeming on 2018/4/25.
 */
public class ConditionUseCase {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();//当前线程释放锁并在此等待
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();//其他线程调用Condition对象的signal（）方法，通知当前线程后，当前线程才从await返回，并在返回前已获得锁
        } finally {
            lock.unlock();
        }
    }

}
