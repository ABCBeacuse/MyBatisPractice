package com.mybatis.mapper;

import com.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Employee 增删改查接口
 */
public interface EmployeeAllMapper {

    /**
     * 根据 id 查询 Employee 雇员信息
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

    /**
     * 根据 id、lastName 查询 Employee 雇员信息
     *
     * @param id
     * @param lastName
     * @return
     */
    Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    /**
     * 根据传入的 Map 所包含的信息, 来查询雇员信息
     *
     * @return
     */
    Employee getEmployeeByMap(Map<String, Object> map);

    /**
     * 根据 Employee id 列表获取 Employee
     * @param ids
     * @return
     */
    Employee getEmployeeByIds(List<Integer> ids);

    /**
     * 根据传来的 match 使用 sql 语句的 like 来查询结果并返回
     * @param match
     * @return
     */
    List<Employee> getEmployeesByLike(String match);

    /**
     * 根据传来的 match 使用 sql 语句的 like 来查询结果并返回, 并将查询到的结果封装为 Map, key 为列名, value 为其对应的值
     * @return
     */
    Map<String, Object> getEmployeeMapById(Integer id);

    /**
     * 每条记录封装成 Employee, Employee 的属性 lastName 作为 Map 的 key, 每条记录封装成的 Employee 对象作为 Map 的 value
     * @param match
     * @return
     */
    @MapKey("lastName")
    Map<Integer, Employee> getEmployeesMapByLike(String match);

    /**
     * 添加 Employee 雇员信息
     *
     * @param employee
     */
    Long addEmployee(Employee employee);

    /**
     * 更新 Employee 雇员信息
     *
     * @param employee
     */
    Integer updateEmployee(Employee employee);

    /**
     * 根据 id 删除 Employee 雇员信息
     *
     * @param id
     */
    Boolean deleteEmployeeById(Integer id);

}
