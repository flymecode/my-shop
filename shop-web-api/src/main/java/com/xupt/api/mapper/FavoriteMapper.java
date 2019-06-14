package com.xupt.api.mapper;

import com.xupt.domain.item.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/13
 */
@Mapper
public interface FavoriteMapper {

    @Insert("insert into tb_star set item_id = #{id}, user_id = #{userId}")
    void insert(@Param("id") String id, @Param("userId") Long userId);

    @Select("SELECT i.id,i.title,i.sell_point,i.price,i.cid,i.image,i.num FROM tb_star s left join tb_item i on s.item_id = i.id where s.user_id = #{userId}")
    List<Item> search(Long userId);
}
