package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/31
 */
@Data
@ExcelTarget("ChargeDocExcel")
public class ChargeDocExcel implements Serializable {
    private static final long serialVersionUID = 5236704824728384861L;

    @Excel(name = "订单时间", orderNum = "1", width = 10, exportFormat = "yyyy-MM-dd")
    private Date createTime;

    @Excel(name = "订单编码", orderNum = "2", width = 20, needMerge = true)
    private String orderId;

    @Excel(name = "会员名称", orderNum = "3", width = 13)
    private String memberName;

    @Excel(name = "会员手机号", orderNum = "4", width = 13)
    private String payerMobile;

    @Excel(name = "缴费类目", orderNum = "5", width = 18)
    private String categoryName;

    @Excel(name = "二级类型", orderNum = "6", width = 15)
    private String secondaryType;

    @Excel(name = "一级类型", orderNum = "7", width = 15)
    private String firstType;

    @Excel(name = "数量", orderNum = "8")
    private Integer quantity;

    @Excel(name = "有效开始时间", orderNum = "9", width = 12, exportFormat = "yyyy-MM-dd")
    private Date useBeginTime;

    @Excel(name = "有效结束时间", orderNum = "10", width = 12, exportFormat = "yyyy-MM-dd")
    private Date useEndTime;

    @Excel(name = "订单总金额", orderNum = "11")
    private BigDecimal totalAmount;

    @Excel(name = "实收金额", orderNum = "12", width = 10)
    private BigDecimal amount;

    @Excel(name = "收银员", orderNum = "13", width = 20)
    private String cashier;

    @Excel(name = "订单折扣金额", orderNum = "14", width = 10, needMerge = true)
    private BigDecimal discountAmount;

    @Excel(name = "备注", orderNum = "15", width = 20)
    private String remark;

    @Excel(name = "订单状态", orderNum = "16", width = 8, replace = {"待付款_1", "已付款_2", "已取消_3", "退款申请中_4", "已退款_5"})
    private String status;

    @Excel(name = "支付方式", orderNum = "17", width = 15, replace = {"微信_Wechat", "支付宝_Alipay", "银行卡支付_bank", "现金支付_cash", "购物卡支付_card"})
    private String payType;

    @Excel(name = "销售源", orderNum = "18", width = 8)
    private String saleSource;

    @Excel(name = "折扣授权人手机号", orderNum = "19", width = 13)
    private String authorMobile;
}
