package com.mybatis.mapper;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * (Mybatis) 基于注解配置的接口文件
 */
public interface EmployeeMapperAnnotation {

    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(Integer id);

}
