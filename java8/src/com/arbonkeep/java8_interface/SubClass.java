package com.arbonkeep.java8_interface;

/**
 * 定义一个类既继承类MyClass又实现了MyFun（演示类优先）
 */
public class SubClass /*extends MyClass*/ implements MyFun, MyInterface {

    /*
    @Override
    public String getName() {
        return super.getName();
    }*/

    @Override
    public String getName() {
        return MyFun.super.getName();
    }
    //如果重写的是那个接口的getName方法，在调用时，就会调用该类的getName方法

}
