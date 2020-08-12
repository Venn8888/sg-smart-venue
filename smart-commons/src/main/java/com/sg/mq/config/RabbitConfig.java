//package com.yy.mq.config;
//
//import com.yy.framework.constants.RabbitMqConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
///**
// * @ClassName RabbitConfig
// * @Description: TODO
// * @Author admin
// * @Date 2020/7/8
// * @Version V1.0
// **/
//@Configuration
//public class RabbitConfig {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//    @Value("${spring.rabbitmq.queues}")
//    private String queues;
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
//    /**
//     * 针对消费者配置
//     * 1. 设置交换机类型
//     * 2. 将队列绑定到交换机
//     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
//     HeadersExchange ：通过添加属性key-value匹配
//     DirectExchange:按照routingkey分发到指定队列
//     TopicExchange:多关键字匹配
//     */
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(RabbitMqConstant.YY_MY_SYSTEM_MESSAGE_EXCHANGE);
//    }
//    /**
//     * 获取队列A
//     * @return
//     */
//    @Bean
//    public Queue queueA() {
//        return new Queue(queues, true); //队列持久
//    }
//
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitMqConstant.YY_MY_SYSTEM_MESSAGE_ROUTE_KEY);
//    }
//
//}
