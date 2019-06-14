package com.xupt.domain.item;

import com.xupt.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Data
public class ItemCategory extends BaseEntity implements Serializable {
    private Long parentId;
    private Integer status;
    private Integer sortOrder;
    private String name;
    private Boolean isParent;

    private ItemCategory itemCategory;
}
