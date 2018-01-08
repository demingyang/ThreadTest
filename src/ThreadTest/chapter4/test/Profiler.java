package ThreadTest.chapter4.test;

import java.util.concurrent.TimeUnit;

/**
 * Created by YangDeming on 2018/1/8.
 */
public class Profiler {
    // 第一次 get() 方法 调用 时会 进行 初始化（ 如果 set 方法 没有 调用）， 每个 线程 会 调用 一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }


    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(" Cost: " + Profiler.end() + " mills");
    }
}
