package com.xupt.domain.user;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
}
