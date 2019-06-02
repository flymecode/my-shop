package com.xupt.admin.mapper;

import com.xupt.domain.ContentCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Mapper
public interface ContentCategoryMapper {

    @Select("select * from tb_content_category order by parent_id asc, sort_order asc,is_parent desc")
    List<ContentCategory> findAll();
    @Select("select * from tb_content_category where parent_id = #{pid} order by parent_id asc, sort_order asc,is_parent desc")
    List<ContentCategory> findTreeData(Long pid);
}
