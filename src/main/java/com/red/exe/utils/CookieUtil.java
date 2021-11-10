package com.red.exe.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanpeng.zhao
 * <p>
 * cookie工具类
 */
public class CookieUtil {

    /**
     * 设置cookie
     */
    public static void set(HttpServletResponse response,
                           String username,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(username, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request
     * @param username
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                           String username) {

        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(username)){
            return cookieMap.get(username);
        }else {
            return null;
        }
    }

    /**
     * 将cookie封装成map
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
