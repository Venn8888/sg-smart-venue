package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName 日销售明细
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/10
 * @Version V1.0
 **/
@Getter
@Setter
@ExcelTarget("DaySalesItemExcel")
public class DaySalesItemExcel {

    @Excel(name = "订单编号",orderNum = "1",width = 20.0D)
    private String docId;
    @Excel(name = "订单类型",orderNum = "2",width = 20.0D,replace = {"课程_1","购票_2","活动_3"})
    private String docType;
    @Excel(name = "会员姓名",orderNum = "3",width = 20.0D)
    private String memberName;
    @Excel(name = "手机号",orderNum = "4",width = 20.0D)
    private String telephone;
    @Excel(name = "销售员姓名",orderNum = "5",width = 20.0D)
    private String salerName;
    @Excel(name = "折扣（百分比）",orderNum = "6",width = 20.0D)
    private Double discount;
    @Excel(name = "支付方式",orderNum = "7",width = 20.0D,replace = {"微信支付_Wechat","支付宝支付_Alipay","现金支付_cash","购物卡_card","银行卡_bank"})
    private String payType;
    @Excel(name = "单价",orderNum = "8",width = 20.0D)
    private String price;
    @Excel(name = "数量",orderNum = "9",width = 20.0D)
    private Integer num;
    @Excel(name = "实收",orderNum = "10",width = 20.0D)
    private Double amount;
    @Excel(name = "应收",orderNum = "11",width = 20.0D)
    private Double dueAmount;
    @Excel(name = "创建时间",orderNum = "12",width = 20.0D)
    private String createdDate;
    @Excel(name = "折扣（值）",orderNum = "13",width = 20.0D)
    private Double discountValue;
    @Excel(name = "系统授权人",orderNum = "14",width = 20.0D)
    private String sysAuthorize;
    @Excel(name = "授权一级类型",orderNum = "15",width = 20.0D)
    private String oneLevelAuthorize;
    @Excel(name = "授权二级类型",orderNum = "16",width = 20.0D)
    private String twoLevelAuthorize;
    @Excel(name = "授权备注",orderNum = "17",width = 20.0D)
    private String authorizeRemark;
    @Excel(name = "付款备注",orderNum = "18",width = 20.0D)
    private String payRemark;

}
