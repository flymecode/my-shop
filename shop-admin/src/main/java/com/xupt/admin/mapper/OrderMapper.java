package com.xupt.admin.mapper;

import com.xupt.admin.mapper.provider.OrderProvicer;
import com.xupt.domain.order.Order;
import com.xupt.domain.order.OrderForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Mapper
public interface OrderMapper {

    @SelectProvider(type = OrderProvicer.class, method = "serachOrder")
    List<Order> searchOrders(OrderForm orderForm);
}
