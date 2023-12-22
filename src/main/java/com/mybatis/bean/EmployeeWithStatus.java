package com.mybatis.bean;

/**
 * 测试枚举的类型处理器 TypeHandler 的查询和保存
 */
public class EmployeeWithStatus {

    private String name;

    private Integer age;

    // 枚举默认值
    private EmployeeStatus employeeStatus = EmployeeStatus.LOGOUT;

    public EmployeeWithStatus() {
    }

    public EmployeeWithStatus(String name, Integer age) {
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

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
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
