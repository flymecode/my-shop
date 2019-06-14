package com.xupt.domain.item;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Data
public class ItemVO {
    private Long id;
    private String title;
    private String sellPoint;
    private Double price;
    private String image;
    private Integer num;
}
