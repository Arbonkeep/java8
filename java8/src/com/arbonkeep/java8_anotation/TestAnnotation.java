package com.arbonkeep.java8_anotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类注解
 */
public class TestAnnotation {

    @Test
    public void test() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m = clazz.getMethod("show");

        MyAnnotation[] mas = m.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation myAnnotation : mas) {
            System.out.println(myAnnotation.value());
        }

    }


    //重复注解
    @MyAnnotation("hello")
    @MyAnnotation("LALA")
    public void show(@MyAnnotation("ABC")String str) {//类型注解

    }

}
