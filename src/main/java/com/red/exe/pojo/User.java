package com.red.exe.pojo;

import lombok.Data;

/**
 * @author yanpeng.zhao
 * @date 2021/11/9 15:10
 */
public class User {

    /**
     * id列 主键 自增长
     */
    private Integer id;

    /**
     * 用户名列 不为空
     */
    private String username;

    /**
     * 密码列 不为空
     */
    private String password;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "RedUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
