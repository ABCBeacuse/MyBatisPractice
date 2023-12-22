package com.mybatis.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})
})
public class MyFirstInterceptor implements Interceptor {

    /**
     * intercept 拦截，拦截目标对象（四大对象）的目标方法的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstInterceptor 插件的 intercept 方法拦截到的方法：" + invocation.getMethod());
        // 执行目标方法, 最终返回执行值
        Object proceed = invocation.proceed();
        return proceed;
    }

    /**
     * 包装对象（四大对象），生成一个动态代理对象
     * target 为拦截到的对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstInterceptor 插件的 plugin 方法将要包装的对象：" + target);
        // Mybatis 封装了一个方法，借助 Plugin 的 wrap 方法来使用当前 interceptor 包装我们的目标对象。
        Object wrap = Plugin.wrap(target, this);
        // 返回为当前 target 创建的动态代理
        return wrap;
    }

    /**
     * 将插件注册时的 property 属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置时的信息：" + properties);
    }
}
