package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName 学员信息导入出参
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/8
 * @Version V1.0
 **/
@Getter
@Setter
@ExcelTarget("StuInfoExcel")
public class StuInfoExcel {

    @Excel(name = "学员姓名",orderNum = "1",width = 20.0D)
    private String memberName;
    @Excel(name = "学员手机号",orderNum = "2",width = 20.0D)
    private String mobile;
    @Excel(name = "出生日期",orderNum = "3",width = 20.0D)
    private String birthDay;
    @Excel(name = "性别",orderNum = "4",replace = {"男_m", "女_f","保密_s"},width = 20.0D)
    private String gender;
    @Excel(name = "学员等级",orderNum = "5",width = 20.0D)
    private String grade;
    @Excel(name = "学员积分",orderNum = "5",width = 20.0D)
    private Integer point;
    @Excel(name = "学员状态",orderNum = "7",width = 20.0D)
    private String memberStatus;
    @Excel(name = "关联顾问姓名",orderNum = "8",width = 20.0D)
    private String adviserName;
    @Excel(name = "关联顾问编号",orderNum = "9",width = 20.0D)
    private String adviserNum;
    @Excel(name = "关联销售姓名",orderNum = "10",width = 20.0D)
    private String salerName;
    @Excel(name = "关联销售编号",orderNum = "11",width = 20.0D)
    private String salerNum;
    @Excel(name = "课程卡名称",orderNum = "12",width = 20.0D)
    private String courseCardName;
    @Excel(name = "剩余基础课节",orderNum = "13",width = 20.0D)
    private Integer leftBaseCourseNum;
    @Excel(name = "剩余实付金额",orderNum = "14",width = 20.0D)
    private Double leftPayedMoney;
    @Excel(name = "已上课程数",orderNum = "15",width = 20.0D)
    private Integer usedCourseNum;
    @Excel(name = "已上课程金额",orderNum = "16",width = 20.0D)
    private String usedCourseMoney;
    @Excel(name = "已排未上课数量",orderNum = "17",width = 20.0D)
    private Integer noClassNum;
    @Excel(name = "已排未上课金额",orderNum = "18",width = 20.0D)
    private Double noClassMoney;
    @Excel(name = "已退款金额",orderNum = "19",width = 20.0D)
    private Double refundMoney;
    @Excel(name = "已消费课程金额",orderNum = "20",width = 20.0D)
    private Double consumedMoney;
    @Excel(name = "手工核销实付金额",orderNum = "21",width = 20.0D)
    private Double cancellationMoney;

    @Excel(name = "是否有期课在上",orderNum = "22",width = 20.0D)
    private String isRegular;

}
