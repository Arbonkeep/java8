package com.arbonkeep.java8;

import java.util.concurrent.RecursiveTask;

/**
 * 注意：使用Folk/Join框架必须继承RecursiveTask（有返回值）或者Recursiveaction（无返回值）
 */
public class ForkCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private long THRESHOLD;//临界值

    public ForkCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;//定义length，记录长度

        if (length <= THRESHOLD) {//如果长度小于等于临界值就不需要拆分成小任务
            long sum = 0;
            for (int i = 0; i < length ; i++) {
                sum += i;
            }
            return sum;
        }else {//这就是长度大于临界值的情况，需要进行拆分
            long middle = (end + start) / 2;

            //进行拆分，将子任务压入线程（fork）
            ForkCalculate fc = new ForkCalculate(start, middle);
            fc.fork();

            ForkCalculate fc2 = new ForkCalculate(middle + 1, end);
            fc2.fork();

            //进行整合（join）
            return fc.join() + fc2.join();
        }
    }
}
