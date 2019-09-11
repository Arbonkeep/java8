package com.arbonkeep.java8_interface;

public interface MyInterface {
    default String getName() {
        return "hehe";
    }

    public static void show() {
        System.out.println("java8中，接口中可以定义静态方法");
    }
}
