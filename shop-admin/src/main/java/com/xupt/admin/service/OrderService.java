package com.xupt.admin.service;

import com.xupt.common.dto.ResultMap;
import com.xupt.domain.order.OrderForm;

/**
 * @author maxu
 * @date 2019/6/12
 */
public interface OrderService {
    ResultMap listOrders(Integer start, Integer length, Integer draw, OrderForm orderForm);
}
