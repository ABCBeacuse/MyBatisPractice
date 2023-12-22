package com.mybatis.typehandler;

import com.mybatis.bean.EmployeeStatusPlus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义枚举类型的 TypeHandler 类型处理器
 * <p>
 * T 指的是 当前这个类型处理器 被用来处理哪个类型
 * 表示当前这个类型处理器用来处理 EmployeeStatusPlus 这个类型的数据
 */
public class MyEnumTypeHandler extends BaseTypeHandler<EmployeeStatusPlus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EmployeeStatusPlus parameter, JdbcType jdbcType) throws SQLException {
        // 将枚举类对象的 code 属性信息, 当做是这个枚举类对象对应的信息, 存储到数据库中
        ps.setString(i, parameter.getCode());
    }

    @Override
    public EmployeeStatusPlus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 这里需要在 EmployeeStatusPlus 类中定义一个静态方法, 可以根据 code 信息返回一个 EmployeeStatusPlus 对象
        return EmployeeStatusPlus.getEnumByCode(rs.getString(columnName));
    }

    @Override
    public EmployeeStatusPlus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return EmployeeStatusPlus.getEnumByCode(rs.getString(columnIndex));
    }

    @Override
    public EmployeeStatusPlus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return EmployeeStatusPlus.getEnumByCode(cs.getString(columnIndex));
    }
}
