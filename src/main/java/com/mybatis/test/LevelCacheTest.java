package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.EmployeeAllMapper;
import com.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis 一级缓存
 */
public class LevelCacheTest {

    /**
     * 获取一个新的 sqlSession 对象
     *
     * @return
     */
    private SqlSession getSqlSession() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sessionFactory.openSession();
    }

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();

        try {
            // 同一次会话期间 的操作
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(2);
            System.out.println(employee);
            // 之间是一系列的业务逻辑处理
            // 处理完毕后重新再次查询
            Employee employee1 = employeeMapper.getEmployeeById(2);
            System.out.println(employee1);

            System.out.println(employee == employee1);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void test1() throws IOException {
        // 缓存失效的情况一：不同的 sqlSession
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeById(2);
        System.out.println(employee);

        // 获取另一个 sqlSession
        SqlSession sqlSession1 = getSqlSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        Employee employee1 = employeeMapper1.getEmployeeById(2);
        System.out.println(employee1);

        // 使用不同的 sqlSession 执行一样的查询语句, 查看是否还是返回的同一个封装对象
        System.out.println(employee == employee1);
    }

    @Test
    void test2() throws IOException {
        // 缓存失效情况二：同一个 sqlSession, 但是查询条件不同
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeById(2);
        System.out.println(employee);

        // 查询条件不同, 也不会使用一级缓存, 因为此时该 sqlSession 的一级缓存中还没有 id 为 4 的雇员信息, 必须去数据库查询
        Employee employee1 = employeeMapper.getEmployeeById(4);
        System.out.println(employee1);
        // 目前一级缓存中已存在 id 为 1、4 的雇员信息, 当再次查询 id 为 1、4 的雇员信息时, 会使用该 sqlSession 的一级缓存进行查询
        Employee employee2 = employeeMapper.getEmployeeById(4);
        System.out.println(employee2);
        System.out.println(employee1 == employee2);
    }

    @Test
    void test3() throws IOException {
        // 缓存失效的情况三：同一个 sqlSession, 两次查询中间穿插了 增删改 操作。
        SqlSession sqlSession = getSqlSession();
        EmployeeAllMapper employeeMapper = sqlSession.getMapper(EmployeeAllMapper.class);
        Employee employee = employeeMapper.getEmployeeById(4);
        System.out.println(employee);

        // 中间穿插 增删改 操作
        employeeMapper.addEmployee(new Employee(null, "john", "1", "john@email.com"));
        System.out.println("新增数据成功");

        // 再次使用相同的 sqlSession 结合 相同的查询条件 来进行查询
        Employee employee1 = employeeMapper.getEmployeeById(4);
        System.out.println(employee1);
        System.out.println(employee == employee1);
    }

    @Test
    void test4() throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeById(4);
        System.out.println(employee);

        // 清空缓存
        sqlSession.clearCache();

        Employee employee1 = employeeMapper.getEmployeeById(4);
        System.out.println(employee1);
        System.out.println(employee == employee1);
    }
}
