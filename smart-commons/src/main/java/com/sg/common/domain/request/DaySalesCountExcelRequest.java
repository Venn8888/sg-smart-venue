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
public class DaySalesCountExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = 4768495494483718271L;

    //开始时间 yyyy-MM-dd
    private String startTime;
    //结束时间 yyyy-MM-dd
    private String endTime;
    //类型 1-按日期 2-按月份 默认是按日期
    private String type;
}
