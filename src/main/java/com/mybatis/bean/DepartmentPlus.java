package com.mybatis.bean;

import java.util.List;

/**
 * 部门信息, 包含该部门下 员工列表 属性
 */
public class DepartmentPlus {

    private Integer id;

    private String deptName;

    // 部门下员工列表信息
    private List<Employee> employees;

    public DepartmentPlus() {
    }

    public DepartmentPlus(Integer id, String deptName, List<Employee> employees) {
        this.id = id;
        this.deptName = deptName;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "DepartmentPlus{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", employees=" + employees +
                '}';
    }
}
