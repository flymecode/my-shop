package com.xupt.api.mapper;

import com.xupt.domain.item.ItemCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Mapper
public interface ItemCategoryMapper {

    @Select("select id,parent_id,name from tb_item_cat where id = #{cid} and status = 1")
    ItemCategory search(Long cid);
}

