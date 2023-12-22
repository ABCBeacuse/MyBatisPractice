package com.mybatis.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})
})
public class MySecondInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondInterceptor 的 intercept 方法拦截到" + invocation.getMethod());
        // 执行目标方法，最后返回方法执行值
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 包装拦截到的对象, 生成该拦截对象的代理对象, 并返回
        // this 表示使用当前的拦截器, 来为 拦截到的对象生成 proxy 代理对象
        System.out.println("MySecondInterceptor 的 plugin 方法包装" + target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
