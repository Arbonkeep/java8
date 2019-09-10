package com.arbonkeep.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;



public class TestStreamAPI2 {
    //声明存储Employee对象的集合
    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,3982.20),
            new Employee("李四",18,2500.20),
            new Employee("王五",28,9521.20),
            new Employee("赵六",55,5034.20),
            new Employee("田七",42,4344.20),
            new Employee("田七",42,4344.20),
            new Employee("田七",42,4344.20)
    );


    /**
     * 内部迭代：迭代操作由Stream API 完成

     * 外部迭代：由我们自己循环迭代遍历完成的迭代

     * filter：接收Lambda，从流中排除某些元素

     * limit：截断流，使得元素不超过给定数量

     * skip(n)：跳过元素，返回一个扔掉前n个元素的流。若流中元素不够n个，则返回一个空流。与limit(n)互补

     * distinct：筛选，通过流所生成元素的hashCOde()和equals()去除重复元素
     */

    @Test
    public void test1() {
        //1.获取流(通过集合方式获取)
        Stream<Employee> stream = employees.stream()
                                .filter((e) -> {
                                    System.out.println("Stream API 的中间操作");
                                    return e.getAge() > 30;

                                });//中间操作filter

        //终止操作：一次性执行全部内容，称为“惰性求值”
        stream.forEach(System.out::println);
    }

    //测试外部迭代
    @Test
    public void test2() {
        //1.获取迭代器
        Iterator<Employee> iterator = employees.iterator();

        //2.迭代循环遍历
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //测试limit
    @Test
    public void test3() {
        employees.stream()
                          .filter((e) -> {
                              System.out.println("短路");
                              return e.getSalary() > 3000;
                          })
                          .limit(2)
                          .forEach(System.out::println);
        //短路：在我们找到了满足条件的两个元素之后就不再继续迭代
    }

    //测试skip
    @Test
    public void test4() {
        employees.stream().skip(2)
                          .forEach(System.out::println);
    }

    //测试disdinct
    @Test
    public void test5() {
        employees.stream().distinct()//去除重复需要重写hashCode和equals方法
                          .forEach(System.out::println);
    }

    /**
      映射
           map：接收Lambda，将元素转换成其他形式，或者提取信息。接收一个函数作为参数，该函数会被应用到每个函数
                上，并且将其映射成一个新的元素

           flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连成一个流
     */

    //测试映射map
    @Test
    public void test6() {
        //Lambda表达式演示map
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");
        list.stream().map((e) -> e.toUpperCase())//该函数会被应用到每个函数上
                     .forEach(System.out::println);


        System.out.println("---------------------------------");
        //方法引用演示map
        employees.stream().map(Employee::getName)
                          .forEach(System.out::println);

        System.out.println("---------------------------------");

        //利用方法引用获取到每一个字符，最后以流的形式返回
        Stream<Stream<Character>> stream = list.stream().map(TestStreamAPI2::filterCharacter);
        //遍历(流中有流，利用Lambda表达式遍历)
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("---------------------------------");

        //利用flatMap的形式实现获取每一个字符，并以流的形式返回
        list.stream().flatMap(TestStreamAPI2::filterCharacter)
                     .forEach(System.out::println);

    }

    //声明一个获取字符串中每一个字符，并将字符存入集合，将流返回作为返回值的方法

    public static Stream<Character> filterCharacter(String str) {
        //1.创建一个集合
        List<Character> list = new ArrayList<>();

        //2. 遍历字符串，将每一个字符存入集合中
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        //3.将集合转换为流对象返回
        return list.stream();
    }

    //类比：比较list中add与addAll的区别
    @Test
    public void test7() {
        //定义一个集合
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);


        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.addAll(list1);


        System.out.println(list2);

        //add就是直接将集合添加进去，addAll将集合中的每一个元素添加进去

    }

    /**
     * sorted()：自然排序(Comparable)

     * sorted(Comparator com)：定制排序(Comparator)

     */

    @Test
    public void test8() {
        //演示自然排序
        List<Integer> list = Arrays.asList(21,51,35,12,56);

        list.stream().sorted()
                     .forEach(System.out::println);

        System.out.println("-----------------------------------");

        //演示定制排序
        Stream<Employee> stream = employees.stream().sorted((e1, e2) -> {
            if (e1.getName().equals(e2.getName())) {
                return Integer.compare(e1.getAge(), e2.getAge());
            } else {
                return e1.getName().compareTo(e2.getName());
            }
        });

        stream.forEach(System.out::println);

    }

}
