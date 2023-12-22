package com.mybatis.mapper;

import com.mybatis.bean.Employee;

import java.util.List;

/**
 * 使用二级缓存
 */
public interface SecondCacheMapper {

    /**
     * 根据雇员 id 查询雇员信息
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

    /**
     * 批量添加雇员信息
     *
     * @param employees
     * @return
     */
    Integer addEmployees(List<Employee> employees);

}
