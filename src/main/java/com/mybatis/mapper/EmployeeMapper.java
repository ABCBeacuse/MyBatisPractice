package com.mybatis.mapper;

import com.mybatis.bean.Employee;

import java.util.List;

/**
 * Mybatis 的接口式编程
 */
public interface EmployeeMapper {

    /**
     * 根据雇员 Id 查询雇员
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

    /**
     * 根据部门编号查询该部门下 employee 员工信息列表
     *
     * @param deptId
     * @return
     */
    List<Employee> getEmployeesByDeptId(Integer deptId);



}
