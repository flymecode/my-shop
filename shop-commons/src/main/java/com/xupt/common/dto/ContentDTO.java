package com.xupt.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xupt.domain.ContentCategory;
import lombok.Data;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Data
public class ContentDTO implements Serializable {
    private Long id;
    private Long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;

    private String content;
    @JsonIgnore
    private String created;
    @JsonIgnore
    private String updated;
    @JsonIgnore
    private ContentCategory contentCategory;
}
