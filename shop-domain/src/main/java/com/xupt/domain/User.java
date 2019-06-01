package com.xupt.domain;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Data
public class User extends BaseEntity{

    private String username;
    private String email;
    private String phone;

}
