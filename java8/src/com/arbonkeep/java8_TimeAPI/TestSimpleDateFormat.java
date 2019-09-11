package com.arbonkeep.java8_TimeAPI;



import com.sun.org.apache.regexp.internal.RE;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSimpleDateFormat {

    public static void main(String[] args) throws Exception {

        /*
         在之前的日期中有线程安全问题，通过加所解决
         */

        //1.创建日期格式化对象
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
/*
        //2.通过线程池获取
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //return sdf.parse("2019910");

                return DateFormatThreadLocal.convert("2019910");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        //3.创建集合存储
        List<Future<Date>> list = new ArrayList<>();

        //4.提交任务
        for (int i = 0; i < 10 ; i++) {
            list.add(pool.submit(task));
        }

        //遍历
        for (Future<Date> f : list) {
            System.out.println(f.get());
        }
        pool.shutdown();

        */



        //java8之后的实现

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //return sdf.parse("2019910");

                return DateFormatThreadLocal.convert("2019910");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        //3.创建集合存储
        List<Future<Date>> list = new ArrayList<>();

        //4.提交任务
        for (int i = 0; i < 10 ; i++) {
            list.add(pool.submit(task));
        }

        //遍历
        for (Future<Date> f : list) {
            System.out.println(f.get());
        }
        pool.shutdown();

    }
}
