package com.xupt.ui.controller;

import com.xupt.domain.user.UserDTO;
import com.xupt.domain.user.UserLoginVO;
import com.xupt.domain.user.UserRegisterVO;
import com.xupt.ui.api.UserAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    public String login(UserLoginVO user, RedirectAttributes redirectAttributes) throws Exception {
        UserDTO login = UserAPI.login(user);
        log.info(login.toString());
        redirectAttributes.addFlashAttribute("user", login);
        return "redirect:/index";
    }

    @PostMapping("/register")
    public String register(UserRegisterVO user, RedirectAttributes redirectAttributes) throws Exception {
         UserAPI.register(user);
        return "redirect:/to_login";
    }

}
