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
    ResultMap saveUser(UserForm user);

    ResultMap listUsers(Integer page, Integer count, Integer draw);

    ResultMap deleteUsers(String ids);

    ResultMap updateUser(Integer id);

    User getUser(Integer id);
}
