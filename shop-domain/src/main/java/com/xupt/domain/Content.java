package com.xupt.domain;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/2
 */
@Data
public class Content extends BaseEntity {
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
