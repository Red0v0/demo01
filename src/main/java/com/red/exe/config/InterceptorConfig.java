package com.red.exe.config;

import com.red.exe.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：red
 * @date ：2021/11/17 16:21
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                //其他接口token验证
                .addPathPatterns("/test")
                //所有用户都应该放行
                .excludePathPatterns("/login");
    }
}
