package com.xupt.domain.content;

import com.xupt.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContentCategory extends BaseEntity {
    private Long parentId;
    private Integer status;
    private Integer sortOrder;
    private String name;
    private Boolean isParent;

    private ContentCategory contentCategory;
}
