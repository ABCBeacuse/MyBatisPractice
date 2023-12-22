package com.mybatis.mapper;

import com.mybatis.bean.Employee;

import java.util.List;

/**
 * 动态 SQL 的使用（ OGNL 语句 ）
 */
public interface OGNLMapper {

    /**
     * 根据传入的 Employee 对象中不为 null 的属性值来查询 Employee 员工
     * 属性值 = null：表示该属性不作为查询条件
     * 属性值 != null：表示该属性需要作为查询条件, 拼接到 SQL 语句中
     *
     * @param search
     * @return
     */
    Employee getEmployeeById(Employee search);


    /**
     * 尝试将 and 或者 or 写在 <if> 标签的最后, 使用 <trim></trim> 的属性来进行 拼接字符串的裁剪
     *
     * @param search
     * @return
     */
    List<Employee> getEmployeeByObject(Employee search);

    /**
     * 根据传入的 search 查询对象中, 不为 null 的属性进行查询。（ 类似于带有 break 的 switch-case, 仅执行一个 ）
     *
     * @param search
     * @return
     */
    List<Employee> getEmployeeByConditionChoose(Employee search);

    /**
     * 根据传入的 employee 的 id 值，结合 employee 中的非空属性，来 部分更新 指定 id 的 雇员信息
     *
     * @param employee
     * @return
     */
    Boolean partlyUpdateEmployeeMesByObject(Employee employee);

    /**
     * 通过使用 Mybatis 的 <trim> 标签来去除掉 未使用 <set> 标签造成的 更新功能的 拼接语句 结尾可能多余 ","
     *
     * @param employee
     * @return
     */
    Boolean partlyUpdateEmployeeMesByObjectTrim(Employee employee);

}
