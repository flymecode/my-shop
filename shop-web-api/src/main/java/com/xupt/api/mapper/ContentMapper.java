package com.xupt.api.mapper;

import com.xupt.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/5
 */
@Mapper
public interface ContentMapper {
    @Select("select * from tb_content where category_id = #{id}")
    List<Content> selectContentByCategoryId(Long id);
}
