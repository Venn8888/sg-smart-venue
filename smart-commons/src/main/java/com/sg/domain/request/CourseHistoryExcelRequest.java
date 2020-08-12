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
public class CourseHistoryExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = 4768495494483718271L;
    //会员信息
    private String memberId;
    //调整原因
    private String changeReason;
    //操作人
    private String updatedBy;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //课程卡名称
    private String courseCardName;
}
