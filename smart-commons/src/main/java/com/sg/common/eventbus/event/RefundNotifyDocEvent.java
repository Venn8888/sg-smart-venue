package com.sg.common.eventbus.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/6/4
 */
@Data
public class RefundNotifyDocEvent implements Serializable {

    private static final long serialVersionUID = 2502606303390029586L;
    /**
     * 退款订单id
     */
    private String refundId;
    /**
     * 微信退款订单id
     */
    private String wxRefundId;
    /**
     * 退款金额
     */
    private String refundFee;
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 退款时间
     */
    private String successTime;
    /**
     * 退款入账账户
     */
    private String refundRecvAccout;

}
