package com.xupt.domain.item;

import com.xupt.domain.BaseEntity;
import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Data
public class Item extends BaseEntity {
    private String title;
    private String sellPoint;
    private Double price;
    private Integer num;
    private String image;
    private Long cid;
    private Integer status;

}
