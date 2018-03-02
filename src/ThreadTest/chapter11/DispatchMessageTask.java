/*
package ThreadTest.chapter11;

import sun.plugin2.message.Message;

import java.util.concurrent.BlockingQueue;

*/
/**
 * 分发消息 大队列分发到小队列
 * Created by YangDeming on 2018/1/17.
 *//*

public class DispatchMessageTask implements Runnable {
    @Override
    public void run() {
        BlockingQueue<Message> subQueue;
        for (; ; ) {
            // 如果 没有 数据， 则 阻塞 在这里
            Message msg = MsgQueueFactory.getMessageQueue().take();
            // 如 果为 空， 则 表示 没有 Session 机器 连接 上来，
            // 需要 等待， 直到 有 Session 机器 连接 上来
            while ((subQueue = getInstance().getSubQueue()) == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } // 把 消息 放到 小 队列
            try {
                subQueue.put(msg);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    */
/**
     * 均衡 获取 一个 子 队列。 * * @return
     *//*

    public BlockingQueue<Message> getSubQueue() {
        int errorCount = 0;
        for (; ; ) {
            if (subMsgQueues.isEmpty()) {
                return null;
            }
            int index = (int) (System.nanoTime() % subMsgQueues.size());
            try {
                return subMsgQueues.get(index);
            } catch (Exception e) {
                // 出现 错误 表示， 在 获取 队列 大小 之后， 队列 进行 了 一次 删除 操作 LOGGER. error(" 获取 子 队列 出现 错误", e);
                if ((++errorCount) < 3) {
                    continue;
                }

            }
        }
    }
}
*/
