package com.sg.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户帐号
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_account")
public class UserAccountDomain extends Model<UserAccountDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 用户帐号
     */
    @TableId(value = "ACCOUNT_ID", type = IdType.NONE)
    private String accountId;

    /**
     * 类型
     */
    private String type;

    /**
     * 登录帐号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * OPENID
     */
    private String openId;

    /**
     * 业务主体
     */
    private String defaultMainId;

    /**
     * 数据组织
     */
    private String defaultOrgId;

    /**
     * 帐号来源
     */
    private String sources;

    /**
     * 是否启用
     */
    private String isEnable;

    /**
     * 是否认证
     */
    private String isAuthenticated;

    /**
     * 过期日期
     */
    private LocalDateTime expiredDate;

    /**
     * 密码过期时间
     */
    private LocalDateTime passwordExpiredTime;

    /**
     * 备注
     */
    private String memo;

    /**
     * 字段1
     */
    private String udf1;

    /**
     * 字段2
     */
    private String udf2;

    /**
     * 字段3
     */
    private String udf3;

    /**
     * 字段4
     */
    private String udf4;

    /**
     * 字段5
     */
    private String udf5;

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
     * 业务主体
     */
    private String mainId;

    /**
     * 数据组织
     */
    private String dataOrgId;


    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }

}
