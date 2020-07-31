package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/10
 */
@Data
@ExcelTarget(value = "MemberExcel")
public class MemberExcel implements Serializable {

    private static final long serialVersionUID = 8589260732493487338L;

    @Excel(name = "会员编号", orderNum = "1", width = 20)
    private String memberId;

    @Excel(name = "姓名", orderNum = "2", width = 15)
    private String name;

    @Excel(name = "性别", orderNum = "3", replace = {"男_m", "女_f", "保密_s"})
    private String gender;

    @Excel(name = "联系方式", orderNum = "4", width = 15)
    private String mobile;

    @Excel(name = "会员等级", orderNum = "5", width = 15)
    private String grade;

    @Excel(name = "消费金额", orderNum = "6", width = 15)
    private Double totalMoney;

    @Excel(name = "会员积分", orderNum = "7", width = 15)
    private Double point;

    @Excel(name = "联系地址", orderNum = "8", width = 40)
    private String address;

    @Excel(name = "注册时间", orderNum = "9", width = 15, exportFormat = "yyyy-MM-dd")
    private Date registerTime;

    @Excel(name = "注册门店", orderNum = "10", width = 15)
    private String registerStore;

    @Excel(name = "就读学校", orderNum = "11", width = 15)
    private String school;

    @Excel(name = "鞋码", orderNum = "12", width = 15)
    private Integer shoeSize;

    @Excel(name = "学员水平", orderNum = "13", width = 15)
    private String studentPlane;

    @Excel(name = "学员等级", orderNum = "14", width = 15)
    private String studentGrade;

    @Excel(name = "比赛记录", orderNum = "15", width = 15, exportFormat = "yyyy-MM-dd")
    private Date matchRecord;

    @Excel(name = "活动记录", orderNum = "16", width = 15, exportFormat = "yyyy-MM-dd")
    private Date eventRecord;

    @Excel(name = "最近登录时间", orderNum = "17", width = 15, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}
