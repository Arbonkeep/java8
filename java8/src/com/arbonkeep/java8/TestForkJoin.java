package com.arbonkeep.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

    /**
     * fork/join框架
     */
    @Test
    public void test1() {
        Instant start = Instant.now();//java8中定义起始时间

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkCalculate(0, 5000000000L);
        Long sum = pool.invoke(task);//执行任务

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("Fork/Join运行消耗的时间：" + Duration.between(start,end).toMillis());
        //计算结束时间与起始时间的差值
    }

    /**
     * 普通for循环
     */
    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum = 0L;
        for (int i = 0; i < 5000000000L ; i++) {
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("普通for循环运行消耗的时间：" + Duration.between(start,end).toMillis());

    }


    /**
     * java8并行流
     */

    @Test
    public void test3() {
        Instant start = Instant.now();

        LongStream.rangeClosed(0, 5000000000L)
                  .parallel()
                  .reduce(0, Long::sum);

        Instant end = Instant.now();

        System.out.println("java8改进后消耗时间：" + Duration.between(start, end).toMillis());
    }
}
