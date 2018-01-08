package ThreadTest.chapter4.ThreadTest;

import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by YangDeming on 2018/1/8.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    // 线程 池 最大 限制 数
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程 池 默认 的 数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    // 线程 池 最小 的 数量
    private static final int MIN_WORKER_NUMBERS = 1;
    // 这是 一个 工作 列表， 将会 向里 面 插入 工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    // 工作者 列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者 线程 的 数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    // 线程 编号 生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWokers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWokers(workerNum);
    }

    public void execute(Job job) {
        if (job != null) {
            // 添加 一个 工作， 然后 进行 通知
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }



    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs) {
            // 限制 新增 的 Worker 数量 不能 超过 最大值
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWokers(num);
            this.workerNum += num;
        }
    }

    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException(" beyond workNum");
            }
            // 按照 给定 的 数量 停止 Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    // 初始化 线程 工作者
    private void initializeWokers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool- Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 工作者， 负责 消费 任务
    class Worker implements Runnable {
        // 是否 工作
        private volatile boolean running = true;

        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    // 如果 工作者 列表 是 空的， 那么 就 wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException ex) {
                            // 感知 到 外部 对 WorkerThread 的 中断 操作， 返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 取出 一个 Job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception ex) {
                        // 忽略 Job 执行 中的 Exception
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}

