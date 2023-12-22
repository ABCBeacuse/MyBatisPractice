package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.SqlTagMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class SqlTagTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            SqlTagMapper sqlTagMapper = sqlSession.getMapper(SqlTagMapper.class);
            Employee employee = sqlTagMapper.getEmployeeById(2);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

}
