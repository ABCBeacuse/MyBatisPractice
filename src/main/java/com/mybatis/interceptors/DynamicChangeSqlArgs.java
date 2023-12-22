package com.mybatis.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * 动态改变传入的 sql 参数值
 * <p>
 * 场景：调用的查询 id 为 1 的 employee 的方法，实际查询的是 id 为 4 的 employee
 */

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})
})
public class DynamicChangeSqlArgs implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获得到 PreparedStatementHandler 中的 parameterHandler 中的 parameterObject，即可获得传递来的 sql 参数
        // 也可以通过获得 PreparedStatementHandler 中的 parameterHandler 中的 boundSql 中的 parameterObject，但是 boundSql 中的 parameterHandler

        // 这里的 target 是 RoutingStatementHandler 对象
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        // Mybatis 提供了一个类，SystemMetaObject 可以获取对象所有属性的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        // 操作属性元数据, 即可以操作对象的属性（奇怪，这里获取的不是 RoutingStatementHandler 吗，为什么没有添加 delegate 属性）
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println(value);
        // 重新设置 sql 参数的属性信息
        metaObject.setValue("parameterHandler.parameterObject", 11);
        // 最后执行目标方法
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
