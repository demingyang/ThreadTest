package ThreadTest.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by YangDeming on 2018/1/10.
 */
public class Mutex implements Lock {
    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //状态为0时获取锁
        @Override
        public boolean tryAcquire(int acquire) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁将状态设置为0
        @Override
        protected boolean tryRelease(int release) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //返回一个Condition,每个Condition都包含一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }

        // The sync object does all the hard work. We just forward to it.
        private final Sync sync = new Sync();

        public void lock() {
            sync.acquire(1);
        }

        public boolean tryLock() {
            return sync.tryAcquire(1);
        }

        public void unlock() {
            sync.release(1);
        }

        public Condition newCondition() {
            return sync.newCondition();
        }

        public boolean isLocked() {
            return sync.isHeldExclusively();
        }

        public boolean hasQueuedThreads() {
            return sync.hasQueuedThreads();
        }

        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        public boolean tryLock(long timeout, TimeUnit unit)
                throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(timeout));
        }
    }


}
