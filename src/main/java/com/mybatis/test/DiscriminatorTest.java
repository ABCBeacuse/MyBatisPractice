package com.mybatis.test;

import com.mybatis.bean.EmployeePlus;
import com.mybatis.mapper.EmployeePlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class DiscriminatorTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeePlusMapper employeePlusMapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeePlus employeePlus = employeePlusMapper.getEmployeePlusByIdStepDis(8);
            System.out.println(employeePlus);
        } finally {
            sqlSession.close();
        }
    }
}
