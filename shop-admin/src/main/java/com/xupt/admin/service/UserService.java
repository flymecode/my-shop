package com.xupt.admin.service;

import com.xupt.admin.validator.UserForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.User;

/**
 * user service
 * @author maxu
 * @date 2019/5/29
 */
public interface UserService {
    ResultMap saveUser(User user);

    ResultMap listUsers(Integer page, Integer count, Integer draw, UserForm userForm);

    ResultMap deleteUsers(String ids);

    ResultMap updateUser(Integer id);

    ResultMap getUser(Integer id);
}
