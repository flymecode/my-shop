package com.xupt.admin.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/user/form").setViewName("user_form");
        registry.addViewController("/content/form").setViewName("content_form");
        registry.addViewController("/user/list").setViewName("user_list");
        registry.addViewController("/content/list").setViewName("content_list");
        registry.addViewController("/content/form").setViewName("content_form");
        registry.addViewController("/category/sub").setViewName("category_form");
    }

}
