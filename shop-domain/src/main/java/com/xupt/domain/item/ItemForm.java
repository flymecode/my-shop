package com.xupt.domain.item;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Data
public class ItemForm {
    private String title;
    private String sellPoint;
    private Double price;
    private Integer num;
    private String image;
    private Long cid;
}
