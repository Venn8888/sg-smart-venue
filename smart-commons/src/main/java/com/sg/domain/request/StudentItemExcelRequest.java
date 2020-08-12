package com.sg.domain.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName StudentItemExcelRequest
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/14
 * @Version V1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentItemExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = 4768495494483718271L;
    //会员名称
    private String memberName;
    //手机号
    private String telephone;
    //起始时间
    private String startTime;
    //结束时间
    private String endTime;
    //教练名称
    private String coachName;
    //顾问ID
    private String consultantId;
    //销售ID
    private String salesId;
}
