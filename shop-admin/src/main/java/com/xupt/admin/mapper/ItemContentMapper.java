package com.xupt.admin.mapper;

import com.xupt.domain.item.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Mapper
public interface ItemContentMapper {

    @Insert("insert into tb_item" +
            "(title,sell_point,price,num,image,cid,status,created,updated)" +
            "values" +
            "(#{title},#{sellPoint},#{price},#{num},#{image},#{cid},#{status},#{created},#{updated})")
    void saveContent(Item item);

    @Select("select * from tb_item")
    List<Item> searchContents();
}
