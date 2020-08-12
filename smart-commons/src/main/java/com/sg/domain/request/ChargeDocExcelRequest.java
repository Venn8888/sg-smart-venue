package com.sg.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/31
 */
@Data
public class ChargeDocExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = -5024367563512675559L;

    /**
     * 订单开始日期 yyyy-MM-dd
     */
    private String beginOrderDate;
    /**
     * 订单结束日期 yyyy-MM-dd
     */
    private String endOrderDate;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 一级类型
     */
    private String firstType;
    /**
     * 二级类型
     */
    private String secondaryType;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 会员手机号
     */
    private String mobile;
    /**
     * 操作人
     */
    private String cashier;
}
