package com.red.exe.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.red.exe.constant.RedisConstant;
import com.red.exe.mapper.UserMapper;
import com.red.exe.pojo.User;
import com.red.exe.service.UserService;
import com.red.exe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        HashMap<String, String> payloadMap = new HashMap<>();
        if (user != null) {
            int expire = RedisConstant.EXPIRE;
            //把用户信息存入redis
            redisTemplate.opsForValue().set("token_" + username, username, expire, TimeUnit.SECONDS);
            payloadMap.put("id", user.getId().toString());
            payloadMap.put("username", user.getUsername());
            //生成jwt令牌
            System.out.println(JwtUtils.getToken(payloadMap));
        }
        return user;
    }

}
