package com.sg.domain.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName 订单列表入参
 * @Description: TODO
 * @Author admin
 * @Date 2020/8/6
 * @Version V1.0
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderListRequest extends YyPageRequest implements Serializable {

    //订单编码
    private String code;
    //订单日期（格式：yyyy-MM-dd）
    private String createdDateStr;
    //订单名称
    private String name;
    //订单类型
    private String docType;
    //会员名称
    private String memberName;
    //会员手机号
    private String telephone;
    //状态
    private String status;
}
