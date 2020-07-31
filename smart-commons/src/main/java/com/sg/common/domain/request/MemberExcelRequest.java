package com.sg.common.domain.request;

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
public class MemberExcelRequest extends YyPageRequest implements Serializable {

    private static final long serialVersionUID = -1655565138147013466L;

    /**
     * 姓名
     */
    private String name;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 性别{"男_m", "女_f", "保密_s"}
     */
    private String gender;
    /**
     * 会员等级编号
     */
    private String gradeNo;
    /**
     * 注册门店
     */
    private String registerStoreId;
    /**
     * 注册开始时间 yyyy-MM-dd
     */
    private String beginRegisterTime;
    /**
     * 注册结束时间 yyyy-MM-dd
     */
    private String endRegisterTime;
    /**
     * 登录开始时间 yyyy-MM-dd
     */
    private String beginLoginTime;
    /**
     * 登录结束时间 yyyy-MM-dd
     */
    private String endLoginTime;
}
