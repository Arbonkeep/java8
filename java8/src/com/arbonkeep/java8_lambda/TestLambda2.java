package com.arbonkeep.java8_lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

public class TestLambda2 {

    @Test
    public void test1() {
        final int num = 10;//在jdk1.7之前如果在匿名内部类中使用成员变量，必须使用final修饰

        //利用Runnable创建匿名内部类
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World " + num);
            }
        };

        r.run();

        //使用Lambda表达式来实现
        Runnable r1 = () -> System.out.println("Hello Lambda");
        r1.run();
    }
    //测试语法格式三
    @Test
    public void test2() {
        Consumer<String> con = x ->System.out.println(x);
        con.accept("太好用了");
    }

    //测试语法格式四
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        } ;
    }

    //测试语法格式五
    @Test
    public void test4() {
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
    }

    //测试语法格式六，类型推断的演示
    @Test
    public void test5() {
        List<String> list = new ArrayList<>();

        show(new HashMap<>());//自动推断类型为下列方法的类型
    }

    public void show(Map<String,Integer> map) {

    }

    /**
     * 需求：对一个数进行运算(通过Lambda表达式实现)
     *  1.定义一个函数式接口  MyFun
     *  2.定义一个运算的方法  Operation()
     *  3.在测试类中调用方法通过Lambda表达式实现运算功能
     */
    @Test
    public void test6() {
        //实现加法运算
        Integer num = Operation(10,(x) -> x + 20);
        System.out.println(num);

        //实现除法运算
        Integer num2 = Operation(100,(y) -> y/20);
        System.out.println(num2);

    }

    public Integer Operation(Integer x, MyFun mf) {
        return mf.getValue(x);
    }



}

