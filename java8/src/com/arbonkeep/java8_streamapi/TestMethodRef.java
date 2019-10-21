package com.arbonkeep.java8_streamapi;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class TestMethodRef {
    //方法引用
    //对象::实例方法名
    @Test
    public void test1() {
        //使用消费型接口演示
        PrintStream ps1 = System.out;

        //使用Lambda表达式实现
        Consumer<String> con = (x) -> ps1.println(x);

        //使用方法引用方式实现
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("123456");
    }

    @Test
    public void test2() {
        //使用供给型接口演示
        Employee emp = new Employee();

        //使用Lambda表达式实现获取姓名
        Supplier<String> sup1 = () -> emp.getName();
        String name = sup1.get();
        System.out.println(name);

        //使用方法引用的方式实现获取年龄
        Supplier<Integer> sup2 = emp::getAge;
        Integer age = sup2.get();
        System.out.println(age);
    }

    //类::静态方法名
    @Test
    public void test3() {
        //利用Comparator演示
        //使用Lambda表达式实现比较大小
        Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
        //使用方法引用的方式实现比较大小
        Comparator<Integer> com2 = Integer::compare;

    }

    //类::实例方法名
    @Test
    public void test4() {
        //使用Bipredicate演示
        //使用Lambda表达式，实现比较两个字符串是否相等
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        //使用方法引用实现比较两个字符串是否相等
        BiPredicate<String, String> bp2 = String::equals;

        //注意：只有在Lambda表达式参数列表中第一个参数是方法的调用者，第二个参数是传入方法的参数时才能使用
    }


    //构造器引用
    @Test
    public void test5() {
        //使用供给型接口演示
        //使用Lambda表达式创建对象(无参构造)
        Supplier<Employee> sup = () -> new Employee();

        //使用构造器引用创建对象
        Supplier<Employee> sup2 = Employee::new;
        Employee emp = sup2.get();

        System.out.println(emp);

    }

    @Test
    public void test6() {
        //使用函数型接口演示
        //使用Lambda表达式创建对象（有一个参数的构造）
        Function<Integer, Employee> sup = (x) -> new Employee(x);

        //使用构造器引用创建对象(有一个参数的构造)
        Function<Integer, Employee> sup2 = Employee::new;
        Employee emp = sup2.apply(100);
        System.out.println(emp);

        //使用构造器引用创建对象(有两个参数的构造)
        BiFunction<Integer,Integer,Employee> bf = Employee::new;
        Employee emp1 = bf.apply(10, 100);
        System.out.println(emp1);

        //注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致

    }


    //数组引用
    @Test
    public void test7() {
        //使用Lambda表达式创建数组
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] s = fun.apply(10);
        System.out.println(s.length);

        //使用数组引用创建数组
        Function<Integer, String[]> fun2 = String[]::new;
        String[] s2 = fun2.apply(20);
        System.out.println(s2.length);
    }






}
