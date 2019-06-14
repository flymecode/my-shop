package com.xupt.domain.order;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Data
public class OrderForm {
    private String id;
    private Integer status;
    private Long userId;
}
