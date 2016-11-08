package io.khasang.archivarius.model;

public class Employee {
    private int id;
    private int age;
    private String name;
    private String address;
    private float salary;

    public Employee(int id, int age, String name, String address, float salary) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
