package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName SaleTicketOrderListExcel
 * @Description: TODO
 * @Author admin
 * @Date 2020/8/6
 * @Version V1.0
 **/
@Getter
@Setter
@ExcelTarget("SaleTicketOrderListExcel")
public class SaleTicketOrderListExcel implements Serializable {
    @Excel(name = "订单编码",orderNum = "1",width = 20.0D)
    private String code;
    @Excel(name = "订单日期",orderNum = "2",width = 40.0D)
    private String createdDate;
    @Excel(name = "订单名称",orderNum = "3",width = 20.0D)
    private String name;
    @Excel(name = "类型",orderNum = "4",width = 20.0D,replace = {"单次票_ONCE","练习票_EXERCISE","时段票_PERIOD","陪同票_COMPANY"})
    private String udf2;
    @Excel(name = "会员名称",orderNum = "5",width = 20.0D)
    private String memberName;
    @Excel(name = "会员手机号",orderNum = "6",width = 20.0D)
    private String telephone;
    @Excel(name = "数量",orderNum = "7",width = 20.0D)
    private String quantity;
    @Excel(name = "总金额",orderNum = "8",width = 20.0D)
    private String totalAmount;
    @Excel(name = "实付金额",orderNum = "9",width = 20.0D)
    private String amount;
    @Excel(name = "状态",orderNum = "10",width = 20.0D)
    private String status;
    @Excel(name = "员工姓名",orderNum = "11",width = 20.0D)
    private String employeeName;
    @Excel(name = "折扣",orderNum = "12",width = 20.0D)
    private String udf4;
}
