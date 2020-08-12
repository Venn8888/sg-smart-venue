package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 时段票时间包
 * @author: liu weichen
 * @create: 2020/7/9
 */
@Data
@ExcelTarget(value = "TicketTimePackageExcel")
public class TicketTimePackageExcel implements Serializable {
    private static final long serialVersionUID = 7641132232955197988L;
    // 订单编号/购买时间/订单总金额/实付金额/时段票名称/票实例状态/会员手机号/会员姓名/有效天数/失效日期/首次使用日期/剩余时长
    @Excel(name = "订单编号", orderNum = "1", width = 25)
    private String docId;

    @Excel(name = "购买时间", orderNum = "2", exportFormat = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date buyDate;

    @Excel(name = "订单总金额", orderNum = "3")
    private BigDecimal totalAmount;

    @Excel(name = "实付金额", orderNum = "4")
    private BigDecimal amount;

    @Excel(name = "时段票名称", orderNum = "5", width = 45)
    private String ticketName;

    @Excel(name = "票实例状态", orderNum = "6")
    private String ticketStatus;

    @Excel(name = "会员手机号", orderNum = "7", width = 15)
    private String phone;

    @Excel(name = "会员姓名", orderNum = "8", width = 25)
    private String memberName;

    @Excel(name = "有效天数", orderNum = "9")
    private Integer days;

    @Excel(name = "失效日期", orderNum = "10", exportFormat = "yyyy-MM-dd", width = 20)
    private Date expireDate;

    @Excel(name = "首次使用日期", orderNum = "11", exportFormat = "yyyy-MM-dd", width = 20)
    private Date firstDate;

    @Excel(name = "剩余时长", orderNum = "12")
    private Integer leftTime;

}
