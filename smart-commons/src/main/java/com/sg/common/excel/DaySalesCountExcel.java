package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName 日销售汇总
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/10
 * @Version V1.0
 **/
@Setter
@Getter
@ExcelTarget("DaySalesCountExcel")
public class DaySalesCountExcel {

    @Excel(name = "日期",orderNum = "1",width = 20.0D,replace = {"日期_-1"})
    private String updatedDate;

    @Excel(name = "销售类型",orderNum = "2",width = 20.0D,replace = {"课程_1","购票_2","活动_3","类型_-999"})
    private String docType;

    @Excel(name = "现金",orderNum = "3",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double cash;

    @Excel(name = "银行卡",orderNum = "4",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double bankCard;

    @Excel(name = "购物卡",orderNum = "5",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double shopCard;

    @Excel(name = "微信",orderNum = "6",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double wechat;

    @Excel(name = "支付宝",orderNum = "7",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double alipay;

    @Excel(name = "公众号",orderNum = "8",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double publicNumber;

    @Excel(name = "免费",orderNum = "9",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double free;

    @Excel(name = "应收金额",orderNum = "10",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double dueAmount;

    @Excel(name = "实收金额",orderNum = "11",width = 20.0D,replace = {"-_-1.0","-_null"})
    private Double amount;

    @Excel(name = "销售数量",orderNum = "12",width = 20.0D,replace = {"退款_-1.0"})
    private Double saleNum;

}
