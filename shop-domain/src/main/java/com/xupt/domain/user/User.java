package com.xupt.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xupt.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author maxu
 * @date 2019/5/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String username;
    private String email;
    private String phone;
    @JsonIgnore
    private String password;

}
