package com.xupt.api.task;

import com.xupt.api.constant.Constants;
import com.xupt.api.mapper.BrokerMessageLogMapper;
import com.xupt.api.producer.OrderSender;
import com.xupt.common.untils.MapperUtils;
import com.xupt.domain.order.BrokerMessageLog;
import com.xupt.domain.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Component
@Slf4j
public class RetryMessageTask {
    @Autowired
    private OrderSender orderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Scheduled(initialDelay = 3000,fixedDelay = 10000)
    public void reSend() {
        log.info("定时任务------>>开始");
        List<BrokerMessageLog> list = brokerMessageLogMapper.getStatusAndTimeoutMessage();
        list.forEach(messageLog ->{
            if (messageLog.getTryCount() >= 3) {
                // update fail message
                brokerMessageLogMapper.changeBrokerMessageLogStats(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
            } else {
                // resend
                brokerMessageLogMapper.updateCount(messageLog.getMessageId(), new Date());
                try {
                    Order order = MapperUtils.json2pojo(messageLog.getMessage(), Order.class);
                    orderSender.sendOrder(order);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("重新发送---》异常处理");
                }
            }
        });
    }
}
