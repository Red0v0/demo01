package com.red.exe.controller;

import com.red.exe.pojo.User;
import com.red.exe.service.UserService;
import com.red.exe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


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
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password) {
        userService.login(username, password);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("msg", "登陆成功");
        return map;
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String token = request.getHeader("token");
        JwtUtils.verify(token);
        return "success";
    }

    @GetMapping("/logout")
    public Map<String, Object> logout(@RequestParam String username,
                                      HttpServletResponse response) {
        String s = redisTemplate.opsForValue().get("token_" + username);
        HashMap<String, Object> map = new HashMap<>();
        if (s != null){
            redisTemplate.opsForValue().getOperations().delete("token_" + username);
            map.put("status", response.getStatus());
            map.put("msg", "登出成功");
        }else {
            map.put("status", response.getStatus());
            map.put("msg","登出失败，您还未登录");
        }
        return map;
    }

}
