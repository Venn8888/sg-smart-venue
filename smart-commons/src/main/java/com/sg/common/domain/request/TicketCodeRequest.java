package com.sg.common.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 票码请求参数实体
 * @author: liu weichen
 * @create: 2020/6/16
 */
@Data
public class TicketCodeRequest implements Serializable {
    private static final long serialVersionUID = 8003904965675545991L;

    // 门店ID
    private String storeId;

    // 参数：会员ID/票码code
    private String params;

    // 查询类型：GATE-闸机检验、MYCODE-我的券码
    private String checkType;

    // 1-课程、2-票、3-活动
    private String docType;
}
