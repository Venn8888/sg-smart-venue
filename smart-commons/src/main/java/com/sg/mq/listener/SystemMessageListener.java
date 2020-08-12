//package com.yy.mq.listener;
//
//import com.yy.framework.constants.RabbitMqConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName 消息监听
// * @Description: TODO
// * @Author admin
// * @Date 2020/7/8
// * @Version V1.0
// **/
//@Component
//@RabbitListener(queues = {RabbitMqConstant.YY_MY_SYSTEM_MESSAGE_QUEUES_KEY})
//public class SystemMessageListener {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @RabbitHandler
//    public void process(String content) {
//        logger.info("====================>接收到消息：{}",content);
//        // todo 业务逻辑
//    }
//}
