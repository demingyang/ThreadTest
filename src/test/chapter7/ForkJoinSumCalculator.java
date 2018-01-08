package test.chapter7;

import java.util.concurrent.RecursiveTask;

/**
 * Created by YangDeming on 2017/12/28.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESEHOLD = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESEHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();//异步执行子任务
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
//        Long rightResult = rightTask.compute();//同步执行第二个子任务
        rightTask.fork();
        Long rightResult = rightTask.join();
        Long leftResult = leftTask.join();//阻塞读取第一个子任务的结果·

        return rightResult + leftResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
