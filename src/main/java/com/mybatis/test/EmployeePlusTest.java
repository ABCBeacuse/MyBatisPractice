package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.bean.EmployeePlus;
import com.mybatis.mapper.EmployeePlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class EmployeePlusTest {

    @Test
    void testGetEmployeeById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeePlusMapper plusMapper = sqlSession.getMapper(EmployeePlusMapper.class);
            Employee employee = plusMapper.getEmployeeById(2);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void testGetEmployeePlusById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeePlusMapper plusMapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeePlus employee = plusMapper.getEmployeePlusById(6);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void testGetEmployeePlusByIdStep() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeePlusMapper plusMapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeePlus employee = plusMapper.getEmployeePlusByIdStep(6);
            System.out.println(employee.getLastName());
            System.out.println(employee.getDepartment());
        } finally {
            sqlSession.close();
        }
    }
}
