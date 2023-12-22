package com.mybatis.bean;

public enum EmployeeStatusPlus {
    LOGIN("10200", "用户登录"), LOGOUT("10300", "用户登出"), DELETE("10500", "用户被删除");

    private String code;
    private String message;

    EmployeeStatusPlus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static EmployeeStatusPlus getEnumByCode(String code) {
        // values 方法可以从 反编译 枚举类后看出, 内部存在 values 方法
        EmployeeStatusPlus[] values = EmployeeStatusPlus.values();
        for (EmployeeStatusPlus value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "EmployeeStatusPlus{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
