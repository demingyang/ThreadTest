package ThreadTest.chapter4.ConnectionTest;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by YangDeming on 2018/4/24.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 连接 释放 后 需要 进行 通知， 这样 其他 消费者 能够 感知 到 连接 池 中 已经 归还 了 一个 连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
    // 在 mills 内 无法 获取 到 连接， 将会 返回 null
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            // 完全 超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}

