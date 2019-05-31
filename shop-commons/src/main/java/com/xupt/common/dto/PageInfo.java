package com.xupt.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author maxu
 * @date 2019/5/31
 */
@Data
public class PageInfo<T> implements Serializable {
    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private List<T> data;
    private String error;
}
