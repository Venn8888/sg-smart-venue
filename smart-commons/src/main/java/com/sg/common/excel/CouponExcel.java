package com.sg.common.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/8
 */
@Data
@ExcelTarget("CouponExcel")
public class CouponExcel implements Serializable {

    private static final long serialVersionUID = 7958213108754535863L;

    @Excel(name = "地区", orderNum = "1", width = 20)
    private String storeAddress;

    @Excel(name = "门店名称", orderNum = "2", width = 15)
    private String storeName;

    @Excel(name = "卡券编码", orderNum = "3", width = 20)
    private String couponId;

    @Excel(name = "卡卷状态", orderNum = "4", replace = {"未使用_1", "订单锁定_2", "已使用_3", "核销_4", "已过期_5"})
    private String status;

    @Excel(name = "卡券类型", orderNum = "5", width = 12, replace = {"现金券_couponcash", "抵用券_coupongift", "折扣卷_coupondiscount"})
    private String category;

    @Excel(name = "卡券名称", orderNum = "6", width = 15)
    private String couponName;

    @Excel(name = "卡券金额", orderNum = "7")
    private Object amount;

    @Excel(name = "最低消费金额", orderNum = "8", width = 12)
    private Object offsetNeedFull;

    @Excel(name = "会员账号", orderNum = "9", width = 20)
    private String memberId;
    @Excel(name = "发放方式", orderNum = "10",replace = {"完善资料后发放_1","会员自动领取_2","会员发放_3"})
    private String limitType;

    @Excel(name = "领取渠道", orderNum = "11", width = 15,replace = {"晓慧场系统_1","微信小程序_2","晓慧场系统_3"})
    private String collectedChannel;

    @Excel(name = "领取时间", orderNum = "12", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date collectedTime;

    @Excel(name = "有效开始时间", orderNum = "13", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date useBeginTime;

    @Excel(name = "有效结束时间", orderNum = "14", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date useEndTime;

    @Excel(name = "使用时间/核销时间", orderNum = "15", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date usedTime;

    @Excel(name = "使用卡卷订单编号", orderNum = "16", width = 20)
    private String couponOrdId;

    @Excel(name = "使用卡卷产品名称", orderNum = "17", width = 15)
    private String couponOrdName;

}
