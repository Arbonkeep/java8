package com.arbonkeep.java8_lambda;

import org.junit.Test;

import java.util.*;

public class TestLambda1 {
    @Test
    public void test1() {
        //原有的comparator的匿名内部类
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts  = new TreeSet<>(comparator);//将比较器传入集合中

    }

    @Test
    public void test2() {
        //Lambda表达式
        Comparator<Integer> comparator = (x , y) -> Integer.compare(x, y);

        TreeSet<Integer> ts = new TreeSet<>(comparator);

    }


    //定义一个集合存储员工的相关信息
    List<Employee> employees = Arrays.asList(
            new Employee("张三",23,5000.12),
            new Employee("李四", 34, 2500.23),
            new Employee("王五", 32, 3002.43),
            new Employee("赵六", 18, 4251.26),
            new Employee("田七", 42, 3660.56));


    //需求：获取当前员工年龄大于30岁的员工信息
    //定义一个测试类，测试过滤的方法
    @Test
    public void test3() {
        List<Employee> emps = filterEmployees(employees);

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        //创建集合,用于存储返回的值
        List<Employee> emps = new ArrayList<>();

        for (Employee emp: list) {
            if (emp.getAge() >= 30) {
                emps.add(emp);
            }
        }

        return emps;
    }

    //需求：获取当前员工薪水大于3500的员工信息
    public List<Employee> filterEmployeesBySalary(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getSalary() >= 3500) {
                emps.add(emp);
            }
        }

        return emps;
    }

    //优化方式1：策略设计模式
    /**
     * 1.定义一个接口MyPredicate
     * 2.创建接口的实现类，定义限制条件 MyPredicateImp1,MyPredicateImp2
     * 3.定义一个过滤Employee的方法
     * 4.定义测试方法实现功能
     */
    @Test
    public void test4() {
        List<Employee> emps = filterEmployees(employees, new MyPredicateImp1());//年龄为筛选条件
        for (Employee emp : emps) {
            System.out.println(emp);
        }

        System.out.println("----------------------");

        //工资作为筛选条件
        List<Employee> emps2 = filterEmployees(this.employees, new MyPredicateImp2());
        for (Employee emp : emps2) {
            System.out.println(emp);
        }

    }

    public List<Employee> filterEmployees(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if(mp.test(emp)) {
                emps.add(emp);
            }
        }
        return emps;
    }

    //优化方式二：匿名内部类
    @Test
    public void Test5() {
        //此集合存储的是筛选后的值（年龄筛选）
        List<Employee> emps = filterEmployees(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 30;
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }

        System.out.println("----------------------");
        //工资筛选
        List<Employee> emps2 = filterEmployees(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 3500;
            }
        });

        for (Employee emp : emps2) {
            System.out.println(emp);
        }


    }

    //优化方式三：Lambda表达式
    @Test
    public void Test6() {
        //通过年龄来筛选
        List<Employee> list = filterEmployees(this.employees, (e) -> e.getAge() >= 30);
        list.forEach(System.out::println);//循环遍历


        //通过工资来筛选
        List<Employee> list2 = filterEmployees(this.employees, (e) -> e.getSalary() >=3500 );
        list2.forEach(System.out::println);
    }

    //优化方式四：Stream API
    @Test
    public void test7() {
        employees.stream()
                .filter((e) -> e.getSalary() >= 2500)
                .limit(2)//限制只显示两个
                .forEach(System.out::println);

        System.out.println("--------------------");

        employees.stream()
                .map(Employee::getName)//获取名字
                .forEach(System.out::println);//循环遍历
    }





}
