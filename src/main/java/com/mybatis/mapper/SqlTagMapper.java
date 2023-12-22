package com.mybatis.mapper;

import com.mybatis.bean.Employee;

/**
 * 使用 <sql> 标签抽取出 sql 字段, 供后续重复使用
 */
public interface SqlTagMapper {

    /**
     * 根据 雇员id 查询雇员信息
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

}
