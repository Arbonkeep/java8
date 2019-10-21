package com.arbonkeep.test;

import com.arbonkeep.java8_streamapi.Employee;
import com.arbonkeep.java8_streamapi.Employee.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestStringAPI {
    /**
     * 1. 给定一个数字列表，如何返回由一个数的平方构成的列表？
     *  给定[1, 2, 3, 4, 5 ] 返回[1, 4, 9, 16, 25]
     *
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> stream1 = list.stream()
                .map((x) -> x * x);

        stream1.forEach(System.out::println);

    }

    List<Employee> list = Arrays.asList(
            new Employee("张三",33,3982.20, Status.FREE),
            new Employee("李四",18,2500.20, Status.BUSY),
            new Employee("王五",28,9521.20, Status.VOCATION),
            new Employee("赵六",55,5034.20, Status.FREE),
            new Employee("田七",42,4344.20, Status.BUSY)
    );

    /**
     * 2. 怎样用reduce和map方法数一数流中有多少个Employee？
     */
    @Test
    public void test2() {
        Optional<Integer> op = list.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(op.get());
    }

}
