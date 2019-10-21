package com.arbonkeep.java8_streamapi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI1 {


    @Test
    public void test() {
        //创建Stream的4中方式

        //1. 可以通过Collection 系列集合提供的stream() 或 paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2. 通过Arrays中的静态方法stream()来获取数组流
        Employee[] emp = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emp);

        //3. 通过Stream类中的静态方法 of()
        Stream<String> stream3 = Stream.of("abc", "123");

        //4. 创建无限流
        //迭代（Stream中的iterator方法）
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        // 生成（Stream中的generate方法）
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(10).forEach(System.out::println);


    }

}
