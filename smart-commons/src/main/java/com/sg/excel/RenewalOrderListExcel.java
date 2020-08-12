package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName 续场费订单
 * @Description: TODO
 * @Author admin
 * @Date 2020/8/6
 * @Version V1.0
 **/
@Getter
@Setter
@ExcelTarget("RenewalOrderListExcel")
public class RenewalOrderListExcel implements Serializable {

    @Excel(name = "订单编码",orderNum = "1",width = 20.0D)
    private String code;
    @Excel(name = "订单日期",orderNum = "2",width = 40.0D)
    private String createdDate;
    @Excel(name = "订单名称",orderNum = "3",width = 20.0D)
    private String name;
    @Excel(name = "二维码",orderNum = "4",width = 40.0D)
    private String udf1;

    @Excel(name = "总金额",orderNum = "5",width = 20.0D)
    private String totalAmount;
    @Excel(name = "实付金额",orderNum = "6",width = 20.0D)
    private String amount;
    @Excel(name = "状态",orderNum = "7",width = 20.0D)
    private String status;
}
