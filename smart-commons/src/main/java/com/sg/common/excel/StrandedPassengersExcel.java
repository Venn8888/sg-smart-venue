package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 客流滞留量
 * @author: liu weichen
 * @create: 2020/7/9
 */
@Data
@ExcelTarget(value = "StrandedPassengersExcel")
public class StrandedPassengersExcel implements Serializable {
    private static final long serialVersionUID = 9139746475412112170L;

    @Excel(name = "时间刻度", orderNum = "1", width = 20)
    private String time;

    @Excel(name = "进场人数", orderNum = "2")
    private Integer inCount;

    @Excel(name = "出场人数", orderNum = "3")
    private Integer outCount;

    @Excel(name = "客流量", orderNum = "4")
    private Integer klCount;

    @Excel(name = "滞留量", orderNum = "5")
    private Integer zlCount;

    @Excel(name = "类型", orderNum = "6")
    private String instanceType;
}
