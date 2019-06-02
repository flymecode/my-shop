package com.xupt.domain;


import lombok.Data;

import java.util.Date;

/**
 * @author maxu
 * @date 2019/5/31
 */
@Data
public abstract class BaseEntity {
    private Long id;
    private Date created;
    private Date updated;
}
