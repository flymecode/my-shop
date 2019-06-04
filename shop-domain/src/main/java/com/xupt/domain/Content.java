package com.xupt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/6/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Content extends BaseEntity implements Serializable {
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;

    private ContentCategory contentCategory;
}
