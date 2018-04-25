package ThreadTest.chapter4.ConnectionTest;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by YangDeming on 2018/4/24.
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    // 保证 所有 ConnectionRunner 能够 同时 开始
    static CountDownLatch start = new CountDownLatch(1);
    // main 线程 将会 等待 所有 ConnectionRunner 结束 后才 能 继续 执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 线程 数量， 可以 修改 线程 数量 进行 观察
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        //主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
        end.await();
        System.out.println(" total invoke: " + (threadCount * count));
        System.out.println(" got connection: " + got);
        System.out.println(" not got connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        public void run() {
            try {
                start.await();
            } catch (Exception ex) {
            }
            while (count > 0) {
                try {
                    // 从 线程 池 中 获取 连接， 如果 1000ms 内 无法 获取 到， 将会 返回 null
                    // 分别 统计 连接 获取 的 数量 got 和 未 获取 到 的 数量 notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}


