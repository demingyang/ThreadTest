package ThreadTest.chapter8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YangDeming on 2018/1/16.
 */
public class ExchangeTest {
    private static final Exchanger<String> exgr = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行 流水 A";
                    // A 录入 银行 流水 数据
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行 流水 B";
                    // B 录入 银行 流水 数据
                    String A = exgr.exchange(" B");
                    System.out.println(" A 和 B 数据 是否 一致：" + A.equals(B) + "， A 录入 的 是：" + A + "， B 录入 是：" + B);
                } catch (InterruptedException e) {
                }
            }
        });
        threadPool.shutdown();
    }

}
