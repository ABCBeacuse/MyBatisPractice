package com.mybatis.mapper;

import com.mybatis.bean.Employee;
import com.mybatis.bean.EmployeePlus;

public interface EmployeePlusMapper {

    /**
     * 根据 雇员 id 查询雇员信息, 这里不使用 resultType 进行自动封装, 使用 resultMap 自定义封装规则
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

    /**
     * 根据 雇员 id 查询雇员信息，使用 resultMap 自定义 Mybatis 结果集映射规则, 最后将查询到的结果集信息封装到 EmployeePlus 对象实例中
     *
     * @param id
     * @return
     */
    EmployeePlus getEmployeePlusById(Integer id);

    /**
     * 分步查询传入 id 的 employee 雇员信息
     *
     * @param id
     * @return
     */
    EmployeePlus getEmployeePlusByIdStep(Integer id);

    /**
     * 使用带有 discriminator 鉴别器的查询语句
     * 1. 即如果查询出来的 employee 性别为女生，则再去查询该雇员的部门信息
     * 2. 如果查询出来的 employee 性别为男生，则将其 last_name 的值赋值给 email
     *
     * @param id
     * @return
     */
    EmployeePlus getEmployeePlusByIdStepDis(Integer id);

}
