package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 票销售统计
 * @author: liu weichen
 * @create: 2020/7/9
 */
@Data
@ExcelTarget(value = "TicketSaleCountExcel")
public class TicketSaleCountExcel implements Serializable {
    private static final long serialVersionUID = -1792871655666574171L;
    // 销售员 单据类型 单据编号 会员姓名	手机号码	票数量	票单价	应收	折扣（百分比）	折扣（值）	付款备注	实收	支付方式	订单日期
    @Excel(name = "单据类型", orderNum = "1", replace = {"陪同票_COMPANY", "单次票_ONCE", "练习票_EXERCISE", "续场票_RETIME"})
    private String itemType;

    @Excel(name = "单据编号", orderNum = "2", width = 20)
    private String docId;

    @Excel(name = "会员姓名", orderNum = "3", width = 25)
    private String name;

    @Excel(name = "手机号码", orderNum = "4", width = 15)
    private String phone;

    @Excel(name = "销售", orderNum = "3", width = 25)
    private String saler;

    @Excel(name = "票数量", orderNum = "5")
    private Integer count;

    @Excel(name = "票单价", orderNum = "6")
    private BigDecimal price;

    @Excel(name = "应收", orderNum = "7")
    private BigDecimal dueAmount;

    @Excel(name = "折扣（值）", orderNum = "8")
    private BigDecimal discountAmount;

    @Excel(name = "实收", orderNum = "9")
    private BigDecimal amount;

    @Excel(name = "支付方式", orderNum = "10")
    private String channel;

    @Excel(name = "订单日期", orderNum = "11", exportFormat = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date orderDate;
}
