package test.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * Created by YangDeming on 2017/12/28.
 */
public class SumTest {
    public static void main(String[] args) {
        long l = forkJoinSum(20000);
        System.out.println(l);
    }

    private static long forkJoinSum(long n) {
        long[] longs = LongStream.rangeClosed(0, n).toArray();
        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(longs);
        return new ForkJoinPool().invoke(forkJoinSumCalculator);
    }
}
