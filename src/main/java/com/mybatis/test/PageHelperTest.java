package com.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mybatis.bean.Employee;
import com.mybatis.mapper.PageHelperMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {

    @Test
    void test() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PageHelperMapper pageHelperMapper = sqlSession.getMapper(PageHelperMapper.class);
            // 第一种使用方式(查询第一页, 每页 5 条数据), 该方法会返回一个 Page 对象
            Page<Object> page = PageHelper.startPage(1, 5);
            List<Employee> employees = pageHelperMapper.getEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
            System.out.println("当前页码：" + page.getPageNum());
            System.out.println("当前页面记录数：" + page.getPageSize());
            System.out.println("总记录数：" + page.getTotal());
            System.out.println("总页码：" + page.getPages());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    void test2() throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        // 获得到一个可以执行批量操作的 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    }

}
