package com.xupt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author maxu
 * @date 2019/6/4
 */
@SpringBootApplication
@EnableScheduling
public class ApiApplication {
        public static void main(String[] args) {
            SpringApplication.run(ApiApplication.class, args);
        }
}
