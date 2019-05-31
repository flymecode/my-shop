package com.xupt.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private Date created;
    private Date updated;
}
