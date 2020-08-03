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
 * ?????·??o???
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_account_org")
public class AccountOrgDomain extends Model<AccountOrgDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 帐号机构
     */
    @TableId(value = "ACCOUNT_ORG_ID", type = IdType.AUTO)
    private String accountOrgId;

    /**
     * 业务系统
     */
    private String appId;

    /**
     * 类型
     */
    private String type;

    /**
     * 帐号
     */
    private String accountId;

    /**
     * 组织机构
     */
    private String orgId;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 版本
     */
    private BigDecimal version;

    /**
     * 是否可用
     */
    private String isActive;

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
        return this.accountOrgId;
    }

}
