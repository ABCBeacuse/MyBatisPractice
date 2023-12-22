package com.mybatis.test;

import com.mybatis.bean.Department;
import com.mybatis.bean.Employee;
import com.mybatis.bean.EmployeePlus;
import com.mybatis.mapper.ForeachMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <foreach> 标签遍历的测试
 */
public class ForeachTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            ForeachMapper foreachMapper = sqlSession.getMapper(ForeachMapper.class);
            List<Employee> employees = foreachMapper.getEmployeesByIds(Arrays.asList(1, 2, 3, 4));
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            ForeachMapper foreachMapper = sqlSession.getMapper(ForeachMapper.class);
            List<EmployeePlus> list = new ArrayList<>();
            list.add(new EmployeePlus(null, "smith", "0", "smith@email.com", new Department(3)));
            list.add(new EmployeePlus(null, "joker", "1", "joker@email.com", new Department(4)));
            list.add(new EmployeePlus(null, "hero", "0", "hero@email.com", new Department(4)));

            Integer res = foreachMapper.addEmployees(list);
            System.out.println("添加了 " + res + " 个员工信息");
            // 记得需要提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            ForeachMapper foreachMapper = sqlSession.getMapper(ForeachMapper.class);
            List<EmployeePlus> list = new ArrayList<>();
            list.add(new EmployeePlus(null, "smith", "0", "smith@email.com", new Department(3)));
            list.add(new EmployeePlus(null, "joker", "1", "joker@email.com", new Department(4)));
            list.add(new EmployeePlus(null, "hero", "0", "hero@email.com", new Department(4)));

            Integer res = foreachMapper.addEmployeesSecondWay(list);
            System.out.println("添加了 " + res + " 个员工信息");
            // 记得需要提交事务
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            ForeachMapper foreachMapper = sqlSession.getMapper(ForeachMapper.class);
            Employee employee1 = new Employee();
            employee1.setLastName("%e%");
            List<Employee> employees = foreachMapper.getEmployeesByBind(employee1);
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } finally {
            sqlSession.close();
        }
    }
}
