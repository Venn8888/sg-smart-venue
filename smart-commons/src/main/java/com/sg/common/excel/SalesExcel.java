package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/9
 */
@Data
@ExcelTarget("SalesExcel")
public class SalesExcel implements Serializable {

    private static final long serialVersionUID = -7637235391378823804L;

    @Excel(name = "时间", orderNum = "1", width = 15)
    private String date;

    @Excel(name = "类型", orderNum = "2", width = 15, replace = {"单次票_ONCE","时段票_PERIOD","练习票_EXERCISE","陪同票_COMPANY","活动_EVENT","期课_QIKE","课_COURSE"})
    private String type;

    @Excel(name = "数量", orderNum = "3", width = 15)
    private String count;
}
