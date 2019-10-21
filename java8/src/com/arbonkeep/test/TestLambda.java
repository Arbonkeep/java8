package com.arbonkeep.test;

import com.arbonkeep.java8_streamapi.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLambda {
    /*需求：调用Collections的sort方法，通过定制排序比较两个Employee（先按照
            年龄比较，年龄相同按照姓名比较），使用Lambda作为参数传递

           分析：1.定义集合存储创建的emp对象
                2.定义测试方法，在测试方法中利用Lambda表达式进行比较

     */

    //1.定义集合存储创建的emp对象
    List<Employee> emps = Arrays.asList(
            new Employee("张三",33,3982.20),
            new Employee("李四",18,2500.20),
            new Employee("王五",28,9521.20),
            new Employee("赵六",55,5034.20),
            new Employee("田七",42,4344.20)
    );

    //2.定义测试方法，在测试方法中利用Lambda表达式进行比较
    @Test
    public void test1() {
        Collections.sort(emps,(e1, e2) -> {
            if(e1.getAge() == e2.getAge()) {//如果年龄相等就比较姓名
                return e1.getName().compareTo(e2.getName());
            }else {//否则就按找年龄大小比较
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        //遍历集合
        for (Employee emp : emps ) {
            System.out.println(emp);
        }
    }


    /**
     * 需求：处理字符串
     *      分析
     *      1. 声明函数式接口，接口中声明抽象方法，public String getValue(String str)
     *      2. 声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换为大写，
     *          并作为方法的返回值
     *      3. 再将一个字符串的第2个和第4个索引位置进行截取子串
     *
     */

    @Test
    public void test2() {
        //将字母转换为大写
        String s = strHandler("如此之帅，Lambda", (str) -> str.toUpperCase());
        System.out.println(s);

        //截取字符串
        String s1 = strHandler("保持努力，继续加油", (str) -> str.substring(2, 4));
        System.out.println(s1);

    }

    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }


    /**
     * 需求：
     *      1. 声明带两个泛型的函数式接口，泛型为<T,R> T 为参数，R 为返回值
     *      2. 接口中声明对应的抽象方法
     *      3. 在TestLambda中声明方法，使用接口作为参数，计算两个long型参数的和
     *      4. 再计算两个long型参数的乘积
     */
    @Test
    public void test3() {
        //计算两个long型数的和
        op(100L, 200L, (x, y) -> x + y);

        //计算两个long型数的乘积
        op(100, 2, (x,y) -> x * y);
    }

    public void op(long long1, long long2, MyFunction2<Long,Long> mf) {
        System.out.println(mf.getValue(long1,long2));
    }

}
