package com.xupt.domain;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Data
public class ContentCategory extends BaseEntity{
    private Long parentId;
    private Integer status;
    private Integer sortOrder;
    private String name;
    private Boolean isParent;
}
