package com.arbonkeep.java8_interface;

/**
  定义一个类测试类优先
      若一个接口中定义了一个默认方法，而另外一个父类或接口中定义了一个同名的方法时，
         会选择父类中的方法。如果一个父类提供了具体的实现，那么接口中具有相同名称和参数列表的方法
         会被忽略

         接口冲突。如果一个父接口提供一个默认方法，而另外一个接口也提供了一个具有相同名称和参数列表
         的方法（不管方法是否是默认方法），那么必须覆盖该方法来解决冲突
 */
public class TestDefaultInterface {
    public static void main(String[] args) {
        SubClass sc = new SubClass();
        String name = sc.getName();
        System.out.println(name);
        //如果是继承类实现了接口，那么调用输出的就是类中的内容（继承类与实现接口的情况）

        //如果重写的是那个接口的getName方法，在调用时，就会调用该类的getName方法（实现了两个不同接口的情况）
    }
}
