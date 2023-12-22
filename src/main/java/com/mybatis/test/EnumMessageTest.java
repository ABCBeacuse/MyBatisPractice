package com.mybatis.test;

import com.mybatis.bean.EmployeeStatusPlus;
import com.mybatis.bean.EmployeeWithStatusPlus;
import com.mybatis.mapper.EmployeeWithStatusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class EnumMessageTest {

    @Test
    void test() {
        EmployeeStatusPlus login = EmployeeStatusPlus.LOGIN;
        System.out.println(login.name());
        System.out.println(login.ordinal());
        System.out.println(login.getCode());
        System.out.println(login.getMessage());
    }

    @Test
    void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeWithStatusMapper employee = sqlSession.getMapper(EmployeeWithStatusMapper.class);
            employee.AddEmployeeWithStatusPlus(new EmployeeWithStatusPlus("tom", 12));
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
            EmployeeWithStatusMapper employeeMapper = sqlSession.getMapper(EmployeeWithStatusMapper.class);
            EmployeeWithStatusPlus employee = employeeMapper.getEmployeeById(3);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

}
