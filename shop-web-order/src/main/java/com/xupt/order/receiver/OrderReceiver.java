package com.xupt.order.receiver;

import com.rabbitmq.client.Channel;
import com.xupt.domain.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Component
@Slf4j
public class OrderReceiver {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.key}"
    ))
    @RabbitHandler
    public void onMessage(@Payload Order order, Channel channel, @Headers Map<String,Object> headers) throws IOException {
        log.info("----------------->{}",order.getId());
        // 确认id
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手工ack
        channel.basicAck(deliveryTag, false);
        System.out.println(order.toString());
    }
}
