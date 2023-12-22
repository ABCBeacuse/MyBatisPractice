package com.mybatis.test;

import com.mybatis.bean.Employee;
import com.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis 接口编程测试类
 */
public class InterFaceTest {

    @Test
    void test() throws IOException {
        // 1. 根据配置文件获取 SqlSessionFactory 对象
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 通过 SqlSessionFactory 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 通过 SqlSession 对象来获取 EmployeeMapper 接口的实现类对象, 实际上获取到的是 Mybatis 帮忙创建的代理 Proxy 对象
        // 当 interface 接口与 sql 映射文件 xml 进行绑定之后, Mybatis 会帮助创建该接口的代理 Proxy 对象,
        // 通过该 Proxy 对象来执行接口中的抽象方法（抽象方法已经和 sql 标签进行了动态绑定, 相当于已被实现）, 进行增删改查操作。
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(1);
            System.out.println(employeeMapper);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * DEBUG 12-04 22:33:44,468 ==>  Preparing: select id,gender,email,last_name lastName from employee where id = ?  (BaseJdbcLogger.java:145)
     * DEBUG 12-04 22:33:44,485 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:145)
     * DEBUG 12-04 22:33:44,498 <==      Total: 1 (BaseJdbcLogger.java:145)
     * org.apache.ibatis.binding.MapperProxy@6c0d7c83
     * Employee{id=1, lastName='Tom', gender='0', email='tom@xaut.com'}
     */

}
