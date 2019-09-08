package com.arbonkeep.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambda3 {
    //Consumer<T> 消费型接口
    @Test
    public void test1() {
        comsume(1000, (money) -> System.out.println("今天逛街，消费了" + money + "元"));
    }

    public void comsume(double money, Consumer<Double> con) {
         con.accept(money);
    }

    //Supplier<T> 供给型接口
    @Test
    public void test2() {
        List<Integer> nums = getNumList(10, () -> (int)(Math.random() * 100));
        for (Integer num : nums) {
            System.out.println(num);
        }

    }
    //需求：产生指定个数的整数并放入集合
    public List<Integer> getNumList(int num, Supplier<Integer> sp) {
        List<Integer> list = new ArrayList<>();
        //随机产生num个整数
        for (int i = 0; i < num ; i++) {
            int n = sp.get();
            list.add(n);
        }
        return list;
    }

    //Function<T,R> 函数型接口
    @Test
    public void test3() {
        //截取字符串
        String s = strHandler("jixujiayou", (str) -> str.substring(2, 5));
        System.out.println(s);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String , String> fc) {
        String s = fc.apply(str);
        return s;
    }

    //Predicate<T> : 断言型接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("大家好", "哈哈", "继续努力吧，加油", "为何你如此直率");
        //调用方法
        List<String> strs = filterStr(list, (s) -> s.length() > 5);

        for (String str : strs) {
            System.out.println(str);
        }
    }

    //需求：将满足条件的字符串放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        //创建集合用于存储筛选后的字符串
        List<String> strs = new ArrayList<>();
        //遍历list，进行筛选
        for (String str : list) {
            if (pre.test(str)) {
                strs.add(str);
            }
        }
        return strs;
    }
}
