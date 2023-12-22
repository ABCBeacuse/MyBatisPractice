package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.EmployeeAllMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmployeeAll 增删改查接口的测试类
 */
public class EmployeeAllTest {

    @Test
    void testAdd() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Employee employee = new Employee(null, "Jack", "1", "Jack@email.com");
            Long res = employeeAllMapper.addEmployee(employee);
            System.out.println(res);
            System.out.println("获取到的自增主键：" + employee.getId());
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * DEBUG 12-05 23:28:23,206 ==>  Preparing: insert into employee(last_name, gender, email) values (?, ?, ?)  (BaseJdbcLogger.java:145)
     * DEBUG 12-05 23:28:23,221 ==> Parameters: Jack(String), 1(String), Jack@email.com(String) (BaseJdbcLogger.java:145)
     * DEBUG 12-05 23:28:23,224 <==    Updates: 1 (BaseJdbcLogger.java:145)
     * 1
     */

    @Test
    void testUpdate() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Integer res = employeeAllMapper.updateEmployee(new Employee(3, "Jerry", "0", "Jerry@email.com"));
            System.out.println(res);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * DEBUG 12-05 23:30:44,970 ==>  Preparing: update employee set last_name = ?, gender = ?, email = ? where id = ?  (BaseJdbcLogger.java:145)
     * DEBUG 12-05 23:30:44,986 ==> Parameters: Jerry(String), 0(String), Jerry@email.com(String), 1(Integer) (BaseJdbcLogger.java:145)
     * DEBUG 12-05 23:30:44,988 <==    Updates: 1 (BaseJdbcLogger.java:145)
     * 1
     */

    @Test
    void testDelete() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Boolean res = employeeAllMapper.deleteEmployeeById(3);
            System.out.println(res);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * DEBUG 12-05 23:31:44,824 ==>  Preparing: delete from employee where id = ?  (BaseJdbcLogger.java:145)
     * DEBUG 12-05 23:31:44,841 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:145)
     * DEBUG 12-05 23:31:44,843 <==    Updates: 1 (BaseJdbcLogger.java:145)
     * true
     */

    @Test
    void testSelectMulParam() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Employee employee = employeeAllMapper.getEmployeeByIdAndLastName(4, "Jack");
            System.out.println(employee);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * DEBUG 12-06 23:26:35,345 ==>  Preparing: select * from employee where id = ? and last_name = ?  (BaseJdbcLogger.java:145)
     * DEBUG 12-06 23:26:35,361 ==> Parameters: 4(Integer), Jack(String) (BaseJdbcLogger.java:145)
     * DEBUG 12-06 23:26:35,375 <==      Total: 1 (BaseJdbcLogger.java:145)
     * Employee{id=4, lastName='Jack', gender='1', email='Jack@email.com'}
     */

    @Test
    void testSelectMap() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", 4);
            map.put("lastName", "Jack");
            map.put("tableName", "employee");
            Employee employee = employeeAllMapper.getEmployeeByMap(map);
            System.out.println(employee);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void testSelectIds() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(2);
            ids.add(4);
            Employee employee = employeeAllMapper.getEmployeeByIds(ids);
            System.out.println(employee);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void testSelectMatch() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            List<Employee> employeeList = employeeAllMapper.getEmployeesByLike("%a%");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void testSelectMapById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Map<String, Object> employee = employeeAllMapper.getEmployeeMapById(2);
            System.out.println(employee);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void testSelectEmployeesMapByLike() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用未传递参数的 openSession() 方法, 获取到的 sqlSession 对象不会自动提交事务, 需要手动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeAllMapper employeeAllMapper = sqlSession.getMapper(EmployeeAllMapper.class);

        try {
            Map<Integer, Employee> employeeMap = employeeAllMapper.getEmployeesMapByLike("%a%");
            System.out.println(employeeMap);
            // 手动提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
