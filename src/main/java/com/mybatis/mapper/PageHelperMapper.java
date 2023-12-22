package com.mybatis.mapper;

import com.mybatis.bean.Employee;

import java.util.List;

public interface PageHelperMapper {

    List<Employee> getEmployees();

}
