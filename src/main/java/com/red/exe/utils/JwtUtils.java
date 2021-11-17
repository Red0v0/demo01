package com.red.exe.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author ：red
 * @date ：2021/11/17 14:26
 */
public class JwtUtils {

    private static final String SING = "token!Red";

    /**
     * 生成token
     * header.payload.sing
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.DATE, 7);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach(builder::withClaim);
        //指定令牌过期时间
        return builder.withExpiresAt(instance.getTime())
                //sign签名
                .sign(Algorithm.HMAC256(SING));
    }

    /**
     * 验证token 合法性
     * 并获取token信息
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

}
