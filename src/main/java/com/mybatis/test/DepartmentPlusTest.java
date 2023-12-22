package com.mybatis.test;

import com.mybatis.bean.DepartmentPlus;
import com.mybatis.mapper.DepartmentPlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class DepartmentPlusTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            DepartmentPlusMapper plusMapper = sqlSession.getMapper(DepartmentPlusMapper.class);
            DepartmentPlus departmentPlus = plusMapper.getDepartmentPlusById(1);
            System.out.println(departmentPlus);
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
            DepartmentPlusMapper plusMapper = sqlSession.getMapper(DepartmentPlusMapper.class);
            DepartmentPlus departmentPlus = plusMapper.getDepartmentPlusByIdStep(1);
            System.out.println(departmentPlus.getDeptName());
//            System.out.println(departmentPlus.getEmployees());
        } finally {
            sqlSession.close();
        }
    }

}
