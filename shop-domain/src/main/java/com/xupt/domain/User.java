package com.xupt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

    private String username;
    private String email;
    private String phone;
    private String password;

}
