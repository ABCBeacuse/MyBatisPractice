package com.mybatis.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("emp")
// 使用 @Alias 注解对 Employee 类重新起别名（Mybatis sql 映射文件 xml 中使用）
public class Employee implements Serializable {

    private static final long serialVersionUID = -7949096505297441165L;
    private Integer id;
    // 这里对应的数据库字段为 last_name
    private String lastName;
    private String gender;
    private String email;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
