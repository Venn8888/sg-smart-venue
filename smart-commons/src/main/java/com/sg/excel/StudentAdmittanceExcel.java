package com.sg.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 学员入行情况
 * @author: liu weichen
 * @create: 2020/7/9
 */
@Data
@ExcelTarget(value = "StudentAdmittanceExcel")
public class StudentAdmittanceExcel implements Serializable {
    private static final long serialVersionUID = -2612297629985418741L;
    //## 学员入场情况
    //#教练名称/销售名称/顾问名称/上课时间起/上课时间止/课程类型/课程规模/课程名称/课节单价(标价)/课节单价(实价)/是否扫码入场/会员姓名/手机号/会员等级/会员年龄
    @Excel(name = "教练名称", orderNum = "1", width = 20)
    private String coachName;

    @Excel(name = "销售名称", orderNum = "2", width = 20)
    private String salerName;

    @Excel(name = "顾问名称", orderNum = "3", width = 20)
    private String consultantName;

    @Excel(name = "上课时间起", orderNum = "4", exportFormat = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date courseStarttime;

    @Excel(name = "上课时间止", orderNum = "5", exportFormat = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date courseEndtime;

    @Excel(name = "课程类型", orderNum = "6")
    private String courseType;

    @Excel(name = "课程规模", orderNum = "7", width = 20)
    private String quantity;

    @Excel(name = "课程名称", orderNum = "8", width = 25)
    private String courseName;

    @Excel(name = "课节单价(标价)", orderNum = "9", width = 18)
    private BigDecimal price;

    @Excel(name = "课节单价(实收)", orderNum = "10", width = 18)
    private BigDecimal amount;

    @Excel(name = "入场时间", orderNum = "11", exportFormat = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date recordTime;

    @Excel(name = "会员姓名", orderNum = "12", width = 25)
    private String memberName;

    @Excel(name = "手机号", orderNum = "13", width = 15)
    private String phone;

    @Excel(name = "会员等级", orderNum = "14")
    private String memberLevel;
}
