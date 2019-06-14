package com.xupt.admin.mapper;

import com.xupt.domain.content.ContentCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Mapper
public interface ContentCategoryMapper {

    @Select("select * from tb_content_category order by parent_id asc, sort_order asc,is_parent desc")
    List<ContentCategory> findAll();
    @Select("select id,name,parent_id,is_parent from tb_content_category where parent_id = #{pid} order by parent_id asc, sort_order asc,is_parent desc")
    List<ContentCategory> findTreeData(Long pid);

    @Select("SELECT a.id,a.name,a.sort_order,b.id as `contentCategory.id`, ifnull(b.name,'/') as ` contentCategory.name` FROM tb_content_category as a left join tb_content_category as b on a.parent_id = b.id where a.id = #{id}")
    ContentCategory findById(Long id);
    @Insert("insert into tb_content_category (parent_id,name,status,sort_order,is_parent,created,updated) values (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated})")
    void insert(ContentCategory category);

//    @Update("update from tb_content_category ")
    void update(ContentCategory category);
    @Update("update tb_content_category set is_parent = 1 where id = #{id}")
    void updateParent(Long id);
    @Select("select is_parent from tb_content_category where id = #{id}")
    ContentCategory isParent(Long id);

    @Delete("delete from tb_content_category where id = #{id}")
    int deleteById(Long id);
}
