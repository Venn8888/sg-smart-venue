package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName 学员课程明细
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/10
 * @Version V1.0
 **/
@Getter
@Setter
@ExcelTarget("StudentItemExcel")
public class StudentItemExcel {

    @Excel(name = "姓名",orderNum = "1",width = 20.0D)
    private String memberName;
    @Excel(name = "手机",orderNum = "2",width = 20.0D)
    private String telephone;
    @Excel(name = "时间起",orderNum = "3",width = 20.0D,databaseFormat = "yyyy-MM-dd")
    private String startTime;
    @Excel(name = "时间止",orderNum = "4",width = 20.0D,databaseFormat = "yyyy-MM-dd")
    private String endTime;
    @Excel(name = "教练",orderNum = "5",width = 20.0D)
    private String coachName;

    private String consultantId;

    @Excel(name = "顾问",orderNum = "6",width = 20.0D)
    private String consultantName;

    private String salesId;
    @Excel(name = "销售",orderNum = "7",width = 20.0D)
    private String salesName;
    @Excel(name = "课程名称",orderNum = "8",width = 20.0D)
    private String courseName;
    @Excel(name = "上课时间起",orderNum = "9",width = 20.0D,databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private String courseStartTime;
    @Excel(name = "上课时间止",orderNum = "10",width = 20.0D,databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private String courseEndTime;
    @Excel(name = "入场状态",orderNum = "11",replace = {"否_0","是_1"},width = 20.0D)
    private String isUsed;

}
