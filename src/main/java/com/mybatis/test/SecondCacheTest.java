package com.mybatis.test;

import com.mybatis.bean.Department;
import com.mybatis.bean.Employee;
import com.mybatis.bean.EmployeePlus;
import com.mybatis.mapper.SecondCacheMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 二级缓存测试
 */
public class SecondCacheTest {

    @Test
    void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 创建两个不同的 sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        SqlSession sqlSession1 = sessionFactory.openSession();

        // getMapper 中的 .class 必须相同, 因为二级缓存是基于 namespace 级别的
        SecondCacheMapper secondCacheMapper = sqlSession.getMapper(SecondCacheMapper.class);
        SecondCacheMapper secondCacheMapper1 = sqlSession1.getMapper(SecondCacheMapper.class);

        // 先使用 sqlSession 来进行查询, 因为是第一次查询, 所以无论是走 sqlSession 对象自身的一级缓存, 还是走 SecondCacheMapper namespace 的二级缓存,
        // 两个缓存 Map 当中都不会存在 id 为 4 的雇员信息的对象
        Employee employee = secondCacheMapper.getEmployeeById(4);
        System.out.println(employee);
        // 关闭第一个 sqlSession, 当该会话关闭时, 会将其查询过的所有结果, 存储到该 SecondCacheMapper namespace 对应的二级缓存下。
        sqlSession.close();

        // 此时 SecondCacheMapper namespace 对应的二级缓存中, 存在 id 为 4 的 employee 雇员信息对象, 所以当第二个 sqlSession 还去查询 id 为 4 的雇员信息时
        // 会从二级缓存中取得（二级缓存的 readOnly 属于为 false, 即通过序列化与反序列方法返回一个缓存中 id 为 4 的 employee 对象的拷贝对象）
        Employee employee1 = secondCacheMapper1.getEmployeeById(4);
        System.out.println(employee1);
        sqlSession1.close();
    }

    @Test
    void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();

        // 同一个 sqlSession, 同一个查询条件
        SecondCacheMapper secondCacheMapper = sqlSession.getMapper(SecondCacheMapper.class);
        Employee employee = secondCacheMapper.getEmployeeById(4);
        System.out.println(employee);

        Employee employee1 = secondCacheMapper.getEmployeeById(4);
        System.out.println(employee1);
        System.out.println(employee == employee1);
    }

    @Test
    void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        SqlSession sqlSession1 = sessionFactory.openSession();

        SecondCacheMapper secondCacheMapper = sqlSession.getMapper(SecondCacheMapper.class);
        SecondCacheMapper secondCacheMapper1 = sqlSession1.getMapper(SecondCacheMapper.class);

        Employee employee = secondCacheMapper.getEmployeeById(4);
        System.out.println(employee);
        // 只有关闭后, SecondCacheMapper namespace 对应的 二级缓存中才会有刚 sqlSession 对象查询的所有内容。之前全部的查询内容是暂存在 sqlSession 对应的一级缓存中
        sqlSession.close();

        // 执行 insert 插入, <insert> 标签的 flushCache 属性默认为 true, 即清空该 sqlSession 的一级缓存, 以及对应的 SecondCacheMapper namespace 的二级缓存。
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(null, "smith", "0", "smith@email.com"));
        list.add(new Employee(null, "joker", "1", "joker@email.com"));
        list.add(new Employee(null, "hero", "0", "hero@email.com"));
        secondCacheMapper1.addEmployees(list);

        Employee employee1 = secondCacheMapper1.getEmployeeById(4);
        System.out.println(employee1);
        sqlSession1.commit();
        sqlSession1.close();

    }

}
