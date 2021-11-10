package com.red.exe.controller;

import com.red.exe.constant.CookieConstant;
import com.red.exe.constant.RedisConstant;
import com.red.exe.pojo.User;
import com.red.exe.service.UserService;
import com.red.exe.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author yanpeng.zhao
 * @date 2021/11/9 15:34
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "success";
    }

    @GetMapping("/login")
    /**@Cacheable(cacheNames = "username", key = "#username")*/
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response) {
        User user = userService.login(username, password);
        if (user == null) {
            return "登陆失败，登陆信息不正确";
        }
        /**设置token至redis*/
        String token = UUID.randomUUID().toString();
        int expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set("token_" + token, username, expire, TimeUnit.SECONDS);
        /**设置cookie*/
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        return "success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        /**1.从cookie里查询*/
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            /**清除redis*/
            redisTemplate.opsForValue().getOperations().delete("token_" + cookie.getValue());
            /**清除cookie*/
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
            return "登出成功";
        } else {
            return "登出失败，您没有登陆";
        }

    }

}
