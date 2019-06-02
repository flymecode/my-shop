package com.xupt.admin.mapper;

import com.xupt.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Mapper
public interface ContentMapper {


    @Select("select * from tb_content")
    List<Content> searchContents();
}
