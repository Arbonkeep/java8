package com.arbonkeep.java8_interface;

/**
 * 定义一个接口，在接口中定义一个默认方法getName()
 */
public interface MyFun {
    default String getName() {
        return "haha";
    }
}
