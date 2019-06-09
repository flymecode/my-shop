package com.xupt.admin.service;

import com.xupt.admin.validator.UserForm;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    ResultMap deleteUser(Long id);

    boolean batchImport(String fileName, MultipartFile file) throws IOException, Exception;
}
