package com.mybatis.bean;

public class EmployeeWithStatusPlus {

    private String name;

    private Integer age;

    // 枚举默认值
    private EmployeeStatusPlus employeeStatus = EmployeeStatusPlus.LOGOUT;

    public EmployeeWithStatusPlus() {
    }

    public EmployeeWithStatusPlus(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EmployeeStatusPlus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatusPlus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @Override
    public String toString() {
        return "EmployeeWithStatus{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", employeeStatus=" + employeeStatus +
                '}';
    }
}
