package com.xupt.admin.mapper;

import com.xupt.domain.content.ContentCategory;
import com.xupt.domain.item.ItemCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Mapper
public interface ItemCategoryMapper {
    @Select("select * from tb_item_cat order by parent_id asc, sort_order asc,is_parent desc")
    List<ItemCategory> findAll();
    @Select("select id,name,parent_id,is_parent from tb_item_cat where parent_id = #{pid} order by parent_id asc, sort_order asc,is_parent desc")
    List<ItemCategory> findTreeData(Long pid);
    @Select("SELECT a.id,a.name,a.sort_order,b.id as `itemCategory.id`, ifnull(b.name,'/') as ` itemCategory.name` FROM tb_item_cat as a left join tb_item_cat as b on a.parent_id = b.id where a.id = #{id}")
    ItemCategory findById(Long id);
    @Select("select is_parent from tb_content_category where id = #{id}")
    ContentCategory isParent(Long parentId);
    void updateParent(Long parentId);
    @Insert("insert into tb_item_cat (parent_id,name,status,sort_order,is_parent,created,updated) values (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated})")
    void insert(ItemCategory category);
    @Delete("delete from tb_item_cat where id = #{id}")
    int deleteById(Long id);
}
