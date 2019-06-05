package com.xupt.ui.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Data
public class UserLoginVO implements Serializable {
    private String username;
    private String password;
}
