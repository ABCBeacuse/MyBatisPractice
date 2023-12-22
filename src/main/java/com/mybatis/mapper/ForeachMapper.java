package com.mybatis.mapper;

import com.mybatis.bean.Employee;
import com.mybatis.bean.EmployeePlus;

import java.util.List;

/**
 * <foreach> 的使用
 */
public interface ForeachMapper {

    /**
     * 根据传递的 雇员 id 集合, 来查询 雇员列表信息
     *
     * @param ids
     * @return
     */
    List<Employee> getEmployeesByIds(List<Integer> ids);

    /**
     * 批量 添加雇员信息, 使用 <foreach> 标签来拼接 SQL 语句, 因为 Mysql 支持 values(),(),()... 语法
     *
     * @param employees
     * @return
     */
    Integer addEmployees(List<EmployeePlus> employees);

    /**
     * 将添加的多条员工信息, 使用 <foreach> 标签分为 多条 insert 语句来进行执行。
     * 并且需要通过配置 MySQL 数据库的连接属性 allowMultiQueries=true, 即允许同时执行由 ";" 分隔的多条 SQL 语句
     *
     * @param employees
     * @return
     */
    Integer addEmployeesSecondWay(List<EmployeePlus> employees);

    /**
     * 测试 Mybatis 内置对象的使用
     *
     * @return
     */
    List<Employee> getEmployeesTestInnerObject(Employee employee);

    /**
     * 模糊查询
     * 仍然使用 Employee 对象进行查询参数的传递, 但是对应的 xml 中使用 <bind> 标签去绑定 OGNL 表达式的值到一个变量
     * <p>
     * 比如：Employee 对象中 lastName 可以仅传递 e, 最后的 SQL 字符串拼接结果中就是 %e%
     *
     * @param employee
     * @return
     */
    List<Employee> getEmployeesByBind(Employee employee);

}
