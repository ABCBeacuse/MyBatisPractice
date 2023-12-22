package com.mybatis.mapper;

import com.mybatis.bean.EmployeeWithStatus;
import com.mybatis.bean.EmployeeWithStatusPlus;

public interface EmployeeWithStatusMapper {

    Boolean AddEmployeeWithStatus(EmployeeWithStatus employee);

    Boolean AddEmployeeWithStatusPlus(EmployeeWithStatusPlus employee);

    EmployeeWithStatusPlus getEmployeeById(Integer id);

}
