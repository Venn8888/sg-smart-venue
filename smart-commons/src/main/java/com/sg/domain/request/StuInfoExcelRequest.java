package com.sg.domain.request;

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
public class StuInfoExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = 4768495494483718271L;
    //学员信息
    private String memberId;
    //学员等级
    private String grade;
    //学员状态
    private String memberStatus;
    //课程卡名称
    private String courseCardName;
    //是否有期课在上 1-是 0-否
    private String isRegular;
    //顾问
    private String adviserId;
    //销售
    private String salerId;
}
