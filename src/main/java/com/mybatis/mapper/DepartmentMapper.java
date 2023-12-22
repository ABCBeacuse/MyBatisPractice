package com.mybatis.mapper;

import com.mybatis.bean.Department;

public interface DepartmentMapper {

    /**
     * 根据 部门号 查询部门信息
     *
     * @param id
     * @return
     */
    Department getDepartmentById(Integer id);



}
