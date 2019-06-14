package com.xupt.api.mapper;

import com.xupt.domain.order.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Mapper
public interface OrderMapper {
    @Insert("insert into tb_order (order_id,payment,status,message_id) values (#{id},#{payment},#{status},#{messageId})")
    void insert(Order order);
}
