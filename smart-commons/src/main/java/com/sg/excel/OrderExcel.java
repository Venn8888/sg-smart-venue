package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/9
 */
@Data
@ExcelTarget("OrderExcel")
public class OrderExcel implements Serializable {

    private static final long serialVersionUID = -4442420269749631181L;

    @Excel(name = "订单编号", orderNum = "1", width = 20)
    private String orderId;

    @Excel(name = "订单日期", orderNum = "2", width = 15, exportFormat = "yyyy-MM-dd")
    private Date orderDate;

    @Excel(name = "主票订单编号", orderNum = "3", width = 40)
    private String code;

    @Excel(name = "会员名称", orderNum = "4", width = 15)
    private String memberName;

    @Excel(name = "会员手机", orderNum = "5", width = 15)
    private String memberMobile;

    @Excel(name = "状态", orderNum = "6", replace = {"待付款_1", "已付款_2", "已取消_3", "退款申请中_4", "已退款_5"})
    private String status;

    @Excel(name = "支付方式", orderNum = "7", width = 10,replace = {"微信_Wechat","支付宝_Alipay","银行卡支付_bank","现金支付_cash","购物卡支付_card"})
    private String payType;

    @Excel(name = "订单金额", orderNum = "8", width = 10)
    private Double totalAmount;

    @Excel(name = "实付价格", orderNum = "9", width = 10)
    private Double amount;

    @Excel(name = "系统折扣", orderNum = "10", width = 10)
    private String discountValue;

    @Excel(name = "折扣授权人", orderNum = "11", width = 10)
    private String discountFrom;

    @Excel(name = "卡卷", orderNum = "12", width = 20)
    private String couponNo;

    @Excel(name = "销售源", orderNum = "13", width = 10)
    private String saleSource;
}
