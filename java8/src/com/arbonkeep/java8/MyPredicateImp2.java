package com.arbonkeep.java8;

public class MyPredicateImp2 implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        //筛选salary
        return employee.getSalary() >= 3500;
    }
}
