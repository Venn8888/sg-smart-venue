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
 * 角色权限
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_authority")
public class RoleAuthorityDomain extends Model<RoleAuthorityDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 角色权限
     */
    @TableId(value = "AUTH_ID", type = IdType.AUTO)
    private String authId;

    /**
     * 系统角色
     */
    private String roleId;

    /**
     * 业务系统
     */
    private String appId;

    /**
     * 业务权限
     */
    private String authorityId;

    /**
     * 版本
     */
    private BigDecimal version;

    /**
     * 是否有效
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
     * 数据组织
     */
    private String dataOrgId;

    /**
     * 业务主体
     */
    private String mainId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;


    @Override
    protected Serializable pkVal() {
        return this.authId;
    }

}
