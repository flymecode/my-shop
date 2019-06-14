package com.xupt.api.controller;

import com.xupt.api.constant.Constants;
import com.xupt.api.service.UserService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.user.User;
import com.xupt.domain.user.UserDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Slf4j
@Controller
@RequestMapping(Constants.VERSION + "/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation("user login")
    @PostMapping("/login")
    public ResponseEntity login(User user,HttpServletRequest request) throws Exception {
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
        HttpSession session = request.getSession();
        session.setAttribute(Constants.USER_SESSION, userDTO);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
