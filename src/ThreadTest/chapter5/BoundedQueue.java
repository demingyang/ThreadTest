package ThreadTest.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by YangDeming on 2018/4/25.
 */
public class BoundedQueue<T> {
    private Object[] items;
    // 添加 的 下标， 删除 的 下标 和数 组 当前 数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    // 添加 一个 元素， 如果 数组 满， 则 添加 线程 进入 等待 状态， 直到 有" 空位"
    public void add(T t) throws InterruptedException {
        lock.lock();//获取锁，确保数组修改的可见性和排他性
        try {
            while (count == items.length) notFull.await();//目前是满的
            items[addIndex] = t;
            if (++addIndex == items.length) addIndex = 0;//添加这一元素满了以后就把addIndex置为0
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 由 头部 删除 一个 元素， 如果 数组 空， 则 删除 线程 进入 等待 状态， 直 到有 新 添加 元素
    @SuppressWarnings(" unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) notEmpty.await();//数组为空
            Object x = items[removeIndex];
            if (++removeIndex == items.length) removeIndex = 0;//移除最后一个后removeIndex置为0
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

}
