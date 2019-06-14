package com.xupt.api.controller;

import com.xupt.api.constant.Constants;
import com.xupt.api.service.UserService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("user register")
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity register(User user) {
        ResultMap resultMap = new ResultMap();
        log.info("-------------------->>>>进入");
        userService.register(user);
        resultMap.success().message("注册成功");
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
