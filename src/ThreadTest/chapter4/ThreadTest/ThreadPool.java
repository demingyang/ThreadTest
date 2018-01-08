package ThreadTest.chapter4.ThreadTest;

/**
 * Created by YangDeming on 2018/1/8.
 */
public interface ThreadPool<Job extends Runnable> {
    // 执行 一个 Job， 这个 Job 需要 实现 Runnable
    void execute(Job job);

    // 关闭 线程 池
    void shutdown();

    // 增加 工作者 线程
    void addWorkers(int num);

    // 减少 工作者 线程
    void removeWorker(int num);

    // 得到 正在 等待 执行 的 任务 数量
    int getJobSize();

}
