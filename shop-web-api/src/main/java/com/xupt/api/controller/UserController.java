package com.xupt.api.controller;

import com.xupt.api.constant.Constants;
import com.xupt.api.service.UserService;
import com.xupt.domain.user.User;
import com.xupt.domain.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@RequestMapping(Constants.VERSION +"/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(User user) {
        log.info("-------------------->>>>进入");
        userService.register(user);
    }

    @PostMapping("/login")
    public UserDTO login(User user) {
        User login = userService.login(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(login, userDTO);
        return userDTO;
    }

}
