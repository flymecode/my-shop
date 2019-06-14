package com.xupt.api.mapper;

import com.xupt.domain.item.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Mapper
public interface ItemMapper {
    @Select("select id,title,sell_point,price,cid,image,num from tb_item where cid = #{cid} and status = 1")
    List<Item> search(Long cid);

    @Select("select id,title,sell_point,price,cid,image,num from tb_item where id = #{id} and status = 1")
    Item selectByPrimaryKey(Long id);
}
