package com.mybatis.test;

import com.mybatis.bean.EmployeeWithStatus;
import com.mybatis.mapper.EmployeeWithStatusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class EmployeeWithStatusTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeWithStatusMapper mapper = sqlSession.getMapper(EmployeeWithStatusMapper.class);
            mapper.AddEmployeeWithStatus(new EmployeeWithStatus("tom", 22));
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

}
