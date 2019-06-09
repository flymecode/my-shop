package com.xupt.api.service.impl;

import com.xupt.api.exception.ServerException;
import com.xupt.api.mapper.UserMapper;
import com.xupt.api.service.UserService;
import com.xupt.api.utils.MailUtils;
import com.xupt.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private MailUtils mailUtils;

    @Override
    public User login(User userLogin) {
        User user = userMapper.selectByUsername(userLogin.getUsername());
        if (null == user) {
            log.info("user not found: {}", userLogin.getUsername());
            throw new ServerException("user is not found");
        }
        //校验密码
        if (null != user) {
            boolean checkpw = false;
            checkpw = BCrypt.checkpw(userLogin.getPassword(), user.getPassword());
            if (!checkpw) {
                log.info("password is wrong: {}", userLogin.getUsername());
                throw new ServerException("password is wrong");
            }
        }
        return user;

    }


    @Override
    public User register(User userRegist) {
        //用户名是否已经注册
        if (isExistUserName(userRegist.getUsername())) {
            log.info("the username {} has been registered", userRegist.getUsername());
            throw new ServerException("the username:" + userRegist.getUsername() + " has been registered");
        }
        //邮箱是否已经注册
        if (isExistEmail(userRegist.getEmail())) {
            log.info("the email:" + userRegist.getEmail() + " has been registered");
            throw new ServerException("the email:" + userRegist.getEmail() + " has been registered");
        }
        //密码加密
        userRegist.setPassword(BCrypt.hashpw(userRegist.getPassword(), BCrypt.gensalt()));
        //添加用户
        Date date = new Date();
        userRegist.setCreated(date);
        userRegist.setUpdated(date);
        int insert = userMapper.insert(userRegist);
        if (insert > 0) {
            //添加成功，发送激活邮件
            Map content = new HashMap<String, Object>();
            content.put("username", userRegist.getUsername());
//            content.put("host", serverUtils.getHost());
//            content.put("token", AESUtils.encrypt(tokenUtils.generateContinuousToken(user), null));
//
//            mailUtils.sendTemplateEmail(user.getEmail(),
//                    Constants.USER_ACTIVATE_EMAIL_SUBJECT,
//                    Constants.USER_ACTIVATE_EMAIL_TEMPLATE,
//                    content);

            return userRegist;
        } else {
            log.info("regist fail: {}", userRegist.toString());
            throw new ServerException("regist fail: unspecified error");
        }
    }

    @Override
    public boolean isExistEmail(String email) {
        return userMapper.getUserByEmail(email) > 0;

    }

    @Override
    public  boolean isExistUserName(String username) {
        return userMapper.getUserByName(username) > 0;
    }

}
