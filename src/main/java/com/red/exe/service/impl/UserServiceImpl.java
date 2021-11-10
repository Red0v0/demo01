package com.red.exe.service.impl;

import com.alibaba.fastjson.JSON;
import com.red.exe.mapper.UserMapper;
import com.red.exe.pojo.User;
import com.red.exe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yanpeng.zhao
 * @date 2021/11/9 15:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void register(User user) {
        userMapper.addOne(user);
    }

    @Override
    public User login(String username, String password) {

        User user = userMapper.findOne(username, password);
        String userJoin = JSON.toJSONString(user);
        if (user != null){
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("userCookie", uuid);

        }
        return user;
    }
}
