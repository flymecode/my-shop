package com.xupt.ui.controller;

import com.xupt.ui.api.UserAPI;
import com.xupt.ui.vo.UserLoginVO;
import com.xupt.ui.vo.UserRegisterVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Controller("/user")
public class LoginController {

    @PostMapping("/login")
    public String login(UserLoginVO user) throws Exception {
        UserAPI.login(user);
        return null;
    }

    @PostMapping("/register")
    public String register(UserRegisterVO user) throws Exception {
        UserAPI.register(user);
        return null;
    }
}
