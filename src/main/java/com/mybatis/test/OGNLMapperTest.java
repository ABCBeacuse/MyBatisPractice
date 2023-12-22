package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.OGNLMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OGNLMapperTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            OGNLMapper ognlMapper = sqlSession.getMapper(OGNLMapper.class);
            Employee employee = ognlMapper.getEmployeeById(new Employee(null, "%s%", null, null));
            System.out.println(employee);
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
            OGNLMapper ognlMapper = sqlSession.getMapper(OGNLMapper.class);
            List<Employee> employee = ognlMapper.getEmployeeByObject(new Employee(null, "%s%", null, null));
            for (Employee employee1 : employee) {
                System.out.println(employee1);
            }
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
            OGNLMapper ognlMapper = sqlSession.getMapper(OGNLMapper.class);
            List<Employee> employee = ognlMapper.getEmployeeByConditionChoose(new Employee(null, null, null, null));
            for (Employee employee1 : employee) {
                System.out.println(employee1);
            }
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
            OGNLMapper ognlMapper = sqlSession.getMapper(OGNLMapper.class);
            Boolean res = ognlMapper.partlyUpdateEmployeeMesByObject(new Employee(6, "better", null, null));
            System.out.println(res);
            // 注意需要提交修改
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void test4() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            OGNLMapper ognlMapper = sqlSession.getMapper(OGNLMapper.class);
            Boolean res = ognlMapper.partlyUpdateEmployeeMesByObjectTrim(new Employee(6, "Admin", null, null));
            System.out.println(res);
            // 注意需要提交修改
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
