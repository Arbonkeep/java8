package com.arbonkeep.java8_streamapi;

import com.arbonkeep.java8_streamapi.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

//终止操作
public class TestStreamAPI3 {
    /**
     * 查找与匹配

     * allMatch：检查是否匹配所有元素

     * anyMatch：检查是否至少匹配一个元素

     * noneMatch：检查是否没有匹配所有元素

     * findFirst：返回第一个元素

     * findAny：返回当前流中任意元素

     * count：返回流中元素的总个数

     * max：返回流中最大值

     * min：返回流中最小值
     */

    List<Employee> employees = Arrays.asList(
            new Employee("张三",33,3982.20, Status.FREE),
            new Employee("李四",18,2500.20, Status.BUSY),
            new Employee("王五",28,9521.20, Status.VOCATION),
            new Employee("赵六",55,5034.20, Status.FREE),
            new Employee("田七",42,4344.20, Status.BUSY)
    );

    @Test
    public void test1() {
        //1.测试allMatch
        boolean b1 = employees.stream().allMatch((e1) -> e1.getStatus().equals(Status.BUSY));
        System.out.println(b1);

        //2.测试anyMatch
        boolean b2 = employees.stream().anyMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);

        //3.测试noneMatch
        boolean b3 = employees.stream().noneMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b3);

        //4.测试findFirst(获取按工资排序的第一个Employee的信息)
        Optional<Employee> op1 = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(op1.get());

        //5.测试findAny(获取到状态为FREE的Employee将任意一个返回)
        Optional<Employee> op2 = employees.stream()
                .filter((e) -> e.getStatus().equals(Status.FREE))
                .findAny();

        System.out.println(op2.get());

    }

    @Test
    public void test2() {
        //1.测试count(获取到所有个数)
        long count = employees.stream().count();
        System.out.println(count);

        //2.测试max（获取到所有Employee中，年龄最大的）
        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));

        System.out.println(max.get());

        //3.测试min（获取到所有Employee中，年龄最小的将该年龄获取出来）
        Optional<Integer> min = employees.stream()
                .map(Employee::getAge)
                .min(Integer::compare);
        System.out.println(min.get());


    }

    /**
     归约
     reduce(T identity, BinaryOperator) / reduce(BinaryOperator)：可以将流中元素反复集合起来，得到一个值

     两个方法的区别
        前者需要使用Optional来接受，而后者有指定的返回值类型
     */

    @Test
    public void test3() {
        //演示计算一个集合中元素的总和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        //使用reduce(BinaryOperator)
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        System.out.println(sum.get());

        //使用reduce(T identity, BinaryOperator)
        Integer sum2 = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum2);

        //演示Employee中工资的总和
        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());

    }

    /**
     * 收集
     * collect；将流转换为其他形式。接收一个collection的实现，用于给Stream元素中做汇总的方法
     *
     * 注意：Collector接口种方法的实现决定了如何对流执行收集（如：收集到set，list，hashSet）
     *      但是Collectors实用类提供了很多静态方法，可以方便的创建收集器实例
     */

    @Test
    public void test4() {
        //1.将Employee中名字收集存储在List中
        List<Employee> list = employees.stream()
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("--------------------------");

        //2.将Employee中名字收集存储在set中
        Set<Employee> set = employees.stream()
                .collect(Collectors.toSet());
        list.forEach(System.out::println);

        //3.将Employee中名字收集存储在指定的Collection集合中(HashSet)
        HashSet<Employee> hs = employees.stream()
                .collect(Collectors.toCollection(HashSet::new));

        System.out.println(hs);
    }

    @Test
    public void test5() {
        //总数
        Long count = employees.stream()
                .collect(Collectors.counting());

        System.out.println(count);

        //平均值(年龄)
        Double ave = employees.stream()
                .collect(Collectors.averagingInt(Employee::getAge));

        System.out.println(ave);
        //总和(年龄)
        Integer sum = employees.stream()
                .collect(Collectors.summingInt(Employee::getAge));
        System.out.println(sum);

        //最大值(获取年龄最大的所有信息)
        Optional<Employee> op = employees.stream()
                .collect(Collectors.maxBy((x, y) -> Integer.compare(x.getAge(), y.getAge())));
        System.out.println(op.get());

        //最大值(获取年龄)
        Optional<Integer> op2 = employees.stream()
                .map(Employee::getAge)
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(op.get());


        //最小值()
        Optional<Integer> op3 = employees.stream()
                .map(Employee::getAge)
                .collect(Collectors.minBy(Integer::compare));

        System.out.println(op3.get());
    }

    //分组
    @Test
    public void test6() {
        //按照状态分组
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);

    }

    //多级分组
    @Test
    public void test7() {

        //首先按照状态分组，然后按照年龄分为青年，中年，老年
        Map<Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() <= 25) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public  void test8() {

        //以工资3000为界，分为两个区域（true,false）
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 3000));

        System.out.println(map);
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics dss = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getMax());
    }

    @Test
    public void test10() {
        //获取对象中的名字(连接功能)链接字符串
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));

        System.out.println(str);
    }





}
