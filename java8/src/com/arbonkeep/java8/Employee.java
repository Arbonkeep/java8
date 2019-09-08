package com.arbonkeep.java8;


public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    //空参构造
    public Employee() {
        super();
    }

    public Employee(int age) {
        this.age = age;
    }

    public Employee(int id ,int age) {
        this.id = id;
        this.age = age;
    }

    //有参构造(三个参数)
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
