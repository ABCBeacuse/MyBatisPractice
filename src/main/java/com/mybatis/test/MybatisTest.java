package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    @Test
    void testMybatis() throws IOException {
        // 1. 根据 XML 配置文件（全局配置文件）创建一个 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 从 SqlSessionFactory 中获取 SqlSession, 能执行已经映射的 SQL 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // sql 唯一标识：statement – Unique identifier matching the statement to use.
        // (唯一标识一般是 mapper 中的语句 id 值, 但是为了防止 id 重复, 所以使用 namespace + 语句 id 做为唯一标识)
        // 执行 sql 要用的参数：parameter – A parameter object to pass to the statement.
        try {
            Employee employee = sqlSession.selectOne("com.mybatis.mapper.TestMapper.selectEmployee", 1);
            System.out.println(employee);
        } finally {
            // 重要一步：需要关闭 session 来释放资源
            sqlSession.close();
        }
    }

    /**
     * DEBUG 12-04 22:34:29,298 ==>  Preparing: select id,gender,email,last_name lastName from employee where id = ?  (BaseJdbcLogger.java:145)
     * DEBUG 12-04 22:34:29,314 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:145)
     * DEBUG 12-04 22:34:29,328 <==      Total: 1 (BaseJdbcLogger.java:145)
     * Employee{id=1, lastName='Tom', gender='0', email='tom@xaut.com'}
     */

    @Test
    void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation employeeMapperAnnotation = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = employeeMapperAnnotation.getEmployeeById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
