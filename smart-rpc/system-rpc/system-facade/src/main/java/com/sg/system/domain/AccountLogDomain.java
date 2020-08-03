package com.sg.system.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * ???????—￥??—è?¨
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_account_log")
public class AccountLogDomain extends Model<AccountLogDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 登录日志
     */
    @TableId(value = "LOG_ID", type = IdType.AUTO)
    private String logId;

    /**
     * 业务系统
     */
    private String appId;

    /**
     * 登录帐号
     */
    private String accountId;

    /**
     * 手机序列号
     */
    private String serialNo;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 版本
     */
    private BigDecimal version;

    /**
     * 是否有效
     */
    private String isActive;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 创建日期
     */
    private LocalDateTime createdDate;

    /**
     * 修改日期
     */
    private LocalDateTime updatedDate;

    /**
     * 数据组织
     */
    private String dataOrgId;

    /**
     * 业务主体
     */
    private String mainId;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
