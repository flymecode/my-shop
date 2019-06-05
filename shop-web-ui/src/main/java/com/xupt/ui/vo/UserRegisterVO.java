package com.xupt.ui.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Data
public class UserRegisterVO implements Serializable {
    private String username;
    private String email;
    private String phone;
    private String password;
}
