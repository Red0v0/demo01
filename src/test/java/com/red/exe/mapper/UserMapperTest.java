package com.red.exe.mapper;

import com.red.exe.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yanpeng.zhao
 * @date 2021/11/9 15:14
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void addOne() {
        User user = new User();
        user.setUsername("111");
        user.setPassword("111");
        userMapper.addOne(user);
    }

    @Test
    void findOne(){
        User user = userMapper.findOne("admin", "admin");
        System.out.println(user);
    }
}