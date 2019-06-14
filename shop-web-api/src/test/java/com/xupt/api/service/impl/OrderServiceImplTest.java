package com.xupt.api.service.impl;

import com.xupt.api.service.OrderService;
import com.xupt.domain.order.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author maxu
 * @date 2019/6/11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test

    public void createOrder() throws Exception {
        Order order = new Order();
        order.setId("1111");
        order.setMessageId("0010112");
        order.setPayment(123.0);
        order.setStatus(0);
        orderService.createOrder(order);
    }
}