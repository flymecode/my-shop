package com.xupt.api.producer;

import com.xupt.api.constant.Constants;
import com.xupt.api.mapper.BrokerMessageLogMapper;
import com.xupt.domain.order.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Component
@Log4j2
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private BrokerMessageLogMapper brokerMessageLogMapper;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info(correlationData);
            String id = correlationData.getId();
            if (ack) {
                brokerMessageLogMapper.changeBrokerMessageLogStats(id, Constants.ORDER_SEND_SUCCESS, new Date());
            } else {
                log.info("异常处理....");
            }
        }
    };

    public void sendOrder(Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 消息的唯一ID
        CorrelationData correlationData = new CorrelationData(String.valueOf(order.getMessageId()));
        rabbitTemplate.convertAndSend("order-exchange", "order.ABC", order, correlationData);
    }
}
