package com.xupt.admin.validator;

import lombok.Data;

/**
 * @author maxu
 * @date 2019/6/3
 */
@Data
public class ContentForm {
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
