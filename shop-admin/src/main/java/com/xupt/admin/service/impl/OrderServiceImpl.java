package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.OrderMapper;
import com.xupt.admin.service.OrderService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.order.Order;
import com.xupt.domain.order.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResultMap listOrders(Integer start, Integer length, Integer draw, OrderForm orderForm) {
        ResultMap result = new ResultMap();
        PageHelper.offsetPage(start, length);
        List<Order> orders = orderMapper.searchOrders(orderForm);
        log.info(orders.toString());
        PageInfo<Order> pages = PageInfo.of(orders);
        result.payload(pages.getList());
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotal());
        result.put("recordsFiltered",pages.getTotal());
        result.put("error","");
        return result;
    }
}
