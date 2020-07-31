package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 硬件导入导出excel
 * @author: liu weichen
 * @create: 2020/7/23
 */
@Data
@ExcelTarget(value = "HardwareExcel")
public class HardwareExcel implements Serializable {
    private static final long serialVersionUID = 6602477261430564664L;

    @Excel(name = "门店", orderNum = "1", width = 20)
    private String storeName;

    @Excel(name = "设备名称", orderNum = "2", width = 20)
    private String hardwareName;

    @Excel(name = "设备编号", orderNum = "3", width = 30)
    private String hardwareCode;

    @Excel(name = "设备秘钥", orderNum = "4", width = 30)
    private String hardwareSecret;
}
