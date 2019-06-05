package com.xupt.api.service;

import com.xupt.domain.User;

/**
 * @author maxu
 * @date 2019/6/5
 */
public interface UserService {
    boolean isExistUserName(String name);

    boolean isExistEmail(String name);

    User register(User userRegist);

    User login(User userLogin);
}
