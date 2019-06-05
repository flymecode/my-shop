package com.xupt.api.controller;

import com.xupt.api.constant.Commons;
import com.xupt.api.service.UserService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Slf4j
@RestController
@RequestMapping(Commons.VERSION +"/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(User user) {
        log.info("-------------------->>>>进入");
        User register = userService.register(user);
        return register;
    }

    @PostMapping("/login")
    public ResultMap login(User user) {
        userService.login(user);
        return null;
    }
}
