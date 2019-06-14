package com.xupt.api.service;

import com.xupt.domain.order.Order;

/**
 * @author maxu
 * @date 2019/6/10
 */
public interface OrderService {
    void createOrder(Order order) throws Exception;
}
