package com.xupt.admin.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * user login
 * @author maxu
 * @date 2019/5/28
 */
@Controller
@Slf4j
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam String password, @RequestParam String email) {
        log.info(email);
        log.info(password);
        return "index";
    }
}
