package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName 课程变节出参
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/9
 * @Version V1.0
 **/
@Setter
@Getter
@ExcelTarget("CourseHistoryExcel")
public class CourseHistoryExcel {

    @Excel(name = "课程卡名称",orderNum = "1",width = 20.0D)
    private String courseCardName;

    @Excel(name = "课程名称",orderNum = "2",width = 20.0D)
    private String courseName;
    @Excel(name = "会员名称",orderNum = "3",width = 20.0D)
    private String memberName;
    @Excel(name = "会员手机号",orderNum = "4",width = 20.0D)
    private String telephone;
    @Excel(name = "调整前基础课节",orderNum = "5",width = 20.0D)
    private Double beforeQuantity;
    @Excel(name = "调整基础课节",orderNum = "6",width = 20.0D)
    private Double changedQuantity;
    @Excel(name = "调整后基础课节",orderNum = "7",width = 20.0D)
    private Double afterQuantity;
    @Excel(name = "调整类型",orderNum = "8",width = 20.0D,replace = {"购课_1","排课_2","调课_3","换课_4","退课_5","退款_6"})
    private String changeReason;

    @Excel(name = "操作原因",orderNum = "9",width = 20.0D)
    private String reason;

    @Excel(name = "备注",orderNum = "10",width = 20.0D)
    private String remark;

    @Excel(name = "操作人",orderNum = "11",width = 20.0D)
    private String updatedBy;
    @Excel(name = "操作时间",orderNum = "12",width = 20.0D)
    private String updatedDate;
}
