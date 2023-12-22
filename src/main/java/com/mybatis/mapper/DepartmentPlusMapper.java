package com.mybatis.mapper;

import com.mybatis.bean.DepartmentPlus;

public interface DepartmentPlusMapper {

    /**
     * 根据部门 id 获取部门信息 (包含部门信息下的员工列表)
     *
     * @param id
     * @return
     */
    DepartmentPlus getDepartmentPlusById(Integer id);

    /**
     * 根据部门 id 获取部门信息 (包含部门信息下的员工列表), 分步查询版本
     *
     * @param id
     * @return
     */
    DepartmentPlus getDepartmentPlusByIdStep(Integer id);

}
