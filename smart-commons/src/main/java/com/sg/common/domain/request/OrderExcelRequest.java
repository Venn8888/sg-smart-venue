package com.sg.common.domain.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = -1329424721245716764L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 支付方式{"微信_Wechat","支付宝_Alipay","银行卡支付_bank","现金支付_cash","购物卡支付_card"}
     */
    private String payType;
    /**
     * 订单开始日期 yyyy-MM-dd
     */
    private String beginOrderDate;
    /**
     * 订单结束日期 yyyy-MM-dd
     */
    private String endOrderDate;

}
