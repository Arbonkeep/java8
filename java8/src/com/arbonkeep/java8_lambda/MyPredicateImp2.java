package com.arbonkeep.java8_lambda;

public class MyPredicateImp2 implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        //筛选salary
        return employee.getSalary() >= 3500;
    }
}
