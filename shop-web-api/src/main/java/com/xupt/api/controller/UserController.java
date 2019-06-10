package com.xupt.api.controller;

import com.xupt.api.constant.Constants;
import com.xupt.api.service.UserService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.user.User;
import com.xupt.domain.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public ResponseEntity register(User user) {
        ResultMap resultMap = new ResultMap();
        log.info("-------------------->>>>进入");
        userService.register(user);
        resultMap.success().message("注册成功");
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }

//    @PostMapping("/login")
//    public UserDTO login(User user) {
//        log.info("-------->>login");
//        User login = userService.login(user);
//        UserDTO userDTO = new UserDTO();
//        BeanUtils.copyProperties(login, userDTO);
//        return userDTO;
//    }

    @PostMapping("/login")
    public ResponseEntity login(User user) {
        log.info("-------->>login");
        if (user.getUsername().length() == 11) {
            user.setPhone(user.getUsername());
            user.setUsername(null);
        }
        ResultMap resultMap = new ResultMap();
        User login = userService.login(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(login, userDTO);
        resultMap.success().payload(userDTO);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
