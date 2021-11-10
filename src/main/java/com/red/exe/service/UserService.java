package com.red.exe.service;

import com.red.exe.pojo.User;

/**
 * @author yanpeng.zhao
 * @date 2021/11/9 15:30
 */
public interface UserService {

    /**
     * 注册的service接口
     * @param user 用户
     */
    void register(User user);

    /**
     * 登陆的service
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User login(String username,String password);
}
