package com.sg.domain.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/7/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CouponExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = 4768495494483718271L;

    /**
     * 领取开始时间(yyyy-mm-dd)
     */
    private String beginCollectedTime;

    /**
     * 领取结束时间(yyyy-mm-dd)
     */
    private String endCollectedTime;

    /**
     * 会员账号
     */
    private String memberId;

    /**
     * 卡券名称
     */
    private String couponName;

    /**
     * 领取渠道 {"晓慧场系统_1","微信小程序_2"}
     */
    private String collectedChannel;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 卡卷状态 {未使用_1", "订单锁定_2", "已使用_3", "核销_4", "已过期_5"}
     */
    private String status;

    /**
     * 发放方式{"完善资料后发放_1","会员自动领取_2","会员发放_3"}
     */
    private String limitType;
}
