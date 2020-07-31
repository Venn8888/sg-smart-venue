package com.sg.common.constants;

/**
 * @description: Rabbit MQ 常量
 * @author: liu weichen
 * @create: 2020/6/4
 */
public class RabbitMqConstant {
    // 优惠券转换器
    public static final String YY_COUPON_EXCHANGE = "yy.coupon.exchange";
    // 课程转换器
    public static final String YY_COURSE_EXCHANGE = "yy.course.exchange";
    // 会员转换器
    public static final String YY_MEMBER_EXCHANGE = "yy.member.exchange";
    // 票
    public static final String YY_TICKET_EXCHANGE = "yy.ticket.exchange";
    // 活动
    public static final String YY_EVENT_EXCHANGE = "yy.event.exchange";
    //订单
    public static final String YY_ORDER_EXCHANGE = "yy.order.exchange";
    //我的系统消息
    public static final String YY_MY_SYSTEM_MESSAGE_EXCHANGE="yy.my.exchange";
    public static final String YY_TICKET_ROUTE_KEY = "yy.ticket.route";
    public static final String YY_ORDER_ROUTE_KEY = "yy.order.route";
    //我的系统消息路由Key
    public static final String YY_MY_SYSTEM_MESSAGE_ROUTE_KEY = "yy.my.route";
    //我的系统消息队列
    public static final String YY_MY_SYSTEM_MESSAGE_QUEUES_KEY="my";

}
