package com.sg.common.utils;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName RabbitMq消息发送工具类
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/8
 * @Version V1.0
 **/
@Component
public class RabbitMqUtils {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    /**
     * 推送消息
     * @param exchange
     * @param routKey 路由key
     * @param content 消息内容
     */
    public void sendMsg(String exchange,String routKey,String content){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(exchange,routKey, content, correlationId);
    }

}
