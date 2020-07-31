package com.sg.common.domain.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName StuInfoExcelRequest
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/14
 * @Version V1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DaySalesItemExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = 4768495494483718271L;

    //订单类型 1-课程 2-票 3-活动
    private String docType;
    //会员手机号
    private String telephone;
    //购入买时间 yyyy-MM-dd
    private String updatedDate;
    //销售
    private String saler;
    //支付方式
    private String payType;
}
