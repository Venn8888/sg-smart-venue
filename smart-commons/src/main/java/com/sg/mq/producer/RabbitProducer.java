//package com.yy.mq.producer;
//
//import com.yy.framework.constants.RabbitMqConstant;
//import com.yy.framework.constants.YyConstants;
//import com.yy.framework.constants.YyRedisConstant;
//import com.yy.mq.config.RabbitConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
///**
// * @ClassName rabbit消息提供者
// * @Description: TODO
// * @Author admin
// * @Date 2020/7/8
// * @Version V1.0
// **/
//@Component
//public class RabbitProducer implements RabbitTemplate.ConfirmCallback {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//
//    private RabbitTemplate rabbitTemplate;
//
//    /**
//     * 构造方法注入rabbitTemplate
//     */
//    @Autowired
//    public RabbitProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
//    }
//
//    /**
//     * 回调 如果ConfigurableBeanFactory.SCOPE_PROTOTYPE时，回调会走此方法
//     * @param correlationData
//     * @param b
//     * @param s
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        logger.info(" 回调id:" + correlationData);
//        if (b) {
//            logger.info("===========================>消息消费成功");
//        } else {
//            logger.info("===========================>消息消费失败:" + s);
//        }
//    }
//}
