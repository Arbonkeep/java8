package com.arbonkeep.java8_TimeAPI;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {

    //  LocalDate   LocalTime LocalDateTime
    @Test
    public void test1() {

        //获取当前时间
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        //设置指定时间
        LocalDateTime ldt2 = LocalDateTime.of(2018, 9, 1, 10, 20, 20);
        System.out.println(ldt2);

        //增加年份
        LocalDateTime ldt3 = ldt1.plusYears(1);
        System.out.println(ldt3);

        //减少月份
        LocalDateTime ldt4 = ldt1.minusMonths(1);
        System.out.println(ldt4);

        //获取年月日时分秒
        System.out.println(ldt1.getYear());
        System.out.println(ldt1.getMonthValue());
        System.out.println(ldt1.getDayOfMonth());
        System.out.println(ldt1.getHour());
        System.out.println(ldt1.getMinute());
        System.out.println(ldt1.getSecond());

    }

    //Instant:时间戳（以Unix元年：1970年1月1日0时0分0秒到某个时间之间的毫秒值）
    @Test
    public void test2() {
        //获取当前时间
        Instant time = Instant.now();//默认UTC时区
        System.out.println(time);

        //将时间进行偏移
        OffsetDateTime time2 = time.atOffset(ZoneOffset.ofHours(8));//背景时间偏移8个小时
        System.out.println(time2);

        //获取到时间的毫秒值
        System.out.println(time.toEpochMilli());

        //改变秒
        Instant time3 = Instant.ofEpochSecond(60);
        System.out.println(time3);



    }

    //Duration；计算两个“时间”之间的间隔

    @Test
    public void test3() {
        //使用Instant
        Instant start = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant end = Instant.now();

        Duration time = Duration.between(start, end);

        System.out.println(time.getSeconds());

        System.out.println("--------------------------------");

        //使用LocalTime

        LocalDate l1 = LocalDate.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalDate l2 = LocalDate.now();

        Duration time2 = Duration.between(l1, l2);

        System.out.println(time2.toMillis());

    }

    //Period：计算两个“日期”之间的间隔

    @Test
    public void test4() {
        LocalDate ld1 = LocalDate.of(2011, 5, 1);

        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1, ld2);
        System.out.println(period);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        //8年4个月9天
    }

    //TemporalAdjuster:时间矫正器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();//获取当前时间
        System.out.println(ldt);

        LocalDateTime ldt1 = ldt.withDayOfMonth(11);//将日指定为某一天
        System.out.println(ldt1);

        LocalDateTime ldt2 = ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));//下一个周五是什么时候
        System.out.println(ldt2);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt3 = (LocalDateTime) l;

            DayOfWeek dow = ldt3.getDayOfWeek();

            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt3.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt3.plusDays(2);
            } else {
                return ldt3.plusDays(1);
            }
        });

        System.out.println(ldt5);

    }

    //DateTimeFormatter:格式化日期时间

    @Test
    public void test6() {
        //使用java提供的ISO的样式格式化
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime ldt = LocalDateTime.now();
        String dateStr = dtf.format(ldt);
        System.out.println(dateStr);

        System.out.println("------------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm:ss");
        String dateStr2 = dtf2.format(ldt);

        System.out.println(dateStr2);

        //将格式还原
        LocalDateTime ldt2 = ldt.parse(dateStr2, dtf2);
        System.out.println(ldt2);

    }

    //ZoneDate、ZoneTime、ZoneDateTime
    @Test
    public void test() {
        //获取Europe/Monaco的时间
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        System.out.println(ldt);

        //上海与原始时间相差8个小时
        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);//2019-09-10T21:54:34.912+08:00[Asia/Shanghai]

    }

    //查看有多少时区信息
    @Test
    public void test7() {
        Set<String> ids = ZoneId.getAvailableZoneIds();

        ids.forEach(System.out::println);
    }
}
