package com.xupt.api.service.impl;

import com.xupt.api.constant.Constants;
import com.xupt.api.mapper.BrokerMessageLogMapper;
import com.xupt.api.mapper.OrderMapper;
import com.xupt.api.producer.OrderSender;
import com.xupt.api.service.OrderService;
import com.xupt.common.untils.MapperUtils;
import com.xupt.domain.order.BrokerMessageLog;
import com.xupt.domain.order.Order;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;
    @Autowired
    private OrderSender orderSender;

    @Transactional
    public void createOrder(Order order) throws Exception {
        Date date = new Date();
        // order insert 业务数据入库
        orderMapper.insert(order);
        // log insert 构建消息日志记录对象
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        String s = MapperUtils.obj2json(order);
        brokerMessageLog.setMessage(MapperUtils.obj2json(order));
        brokerMessageLog.setStatus(Constants.ORDER_SENDING); // 设置订单的发送状态为0，表示发送中
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(date, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreated(date);
        // message log insert
        brokerMessageLogMapper.insert(brokerMessageLog);
        // order message sender
        orderSender.sendOrder(order);
    }
}
