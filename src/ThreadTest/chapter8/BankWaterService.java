package ThreadTest.chapter8;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by YangDeming on 2018/1/16.
 */
public class BankWaterService implements Runnable {
    /**
     * 创建 4 个 屏障， 处理 完 之后 执行 当前 类 的 run 方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);
    /**
     * 假设 只有 4 个 sheet， 所以 只 启动 4 个 线程
     */
    private Executor executor = Executors.newFixedThreadPool(4);
    /**
     * 保存 每个 sheet 计 算出 的 银 流 结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 计算 当前 sheet 的 银 流 数据， 计算 代码 省略
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    // 银 流 计算 完成， 插入 一个 屏障
                    try {
                        c.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        // 汇总 每个 sheet 计 算出 的 结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        } // 将 结果 输出
        sheetBankWaterCount.put(" result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();

    }
}