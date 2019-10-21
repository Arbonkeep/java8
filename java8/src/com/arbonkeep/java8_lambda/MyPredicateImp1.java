package com.arbonkeep.java8_lambda;

public class MyPredicateImp1 implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        //筛选年龄
        return employee.getAge() >= 30;
    }
}
