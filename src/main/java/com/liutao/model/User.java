package com.liutao.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private String password;
    private String dept;

    public User() {

    }

    public User(String name, Integer age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String id, String name, Integer age, String password, String dept) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
