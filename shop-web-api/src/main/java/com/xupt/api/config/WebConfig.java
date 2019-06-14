package com.xupt.api.config;

import com.xupt.api.constant.Constants;
import com.xupt.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author maxu
 * @date 2019/6/13
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(Constants.VERSION + "/user/login",
                        Constants.VERSION + "/user/register",
                        Constants.VERSION + "/content",
                        Constants.VERSION + "/star",
                        Constants.VERSION + "/content/**",
                        Constants.VERSION + "/cart")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Bean
    public LoginInterceptor LoginInterceptor() {
        return new LoginInterceptor();
    }
}
