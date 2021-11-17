package com.red.exe.mapper;

import com.red.exe.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yanpeng.zhao
 * @date 2021/11/9 15:13
 */
@Repository
public interface UserMapper {

    /**
     * 添加一个的抽象方法
     * @param user user对象
     */
    @Insert("insert into user(username,password) " +
            "values(#{username},#{password})")
    void addOne(User user);

    /**
     * 查找一个的抽象方法
     * @param username 用户名
     * @param password 密码
     * @return User 实体User
     */
    @Select("select id,username,password from user " +
            "where username=#{username} and password=#{password}")
    User findOne(String username,String password);
}
