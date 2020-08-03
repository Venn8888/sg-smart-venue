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
 * 权限设置
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_data_dimension")
public class DataDimensionDomain extends Model<DataDimensionDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 权限设置
     */
    @TableId(value = "AUTH_ID", type = IdType.AUTO)
    private String authId;

    /**
     * 权限对象
     */
    private String dimensionId;

    /**
     * 业务系统
     */
    private String appId;

    /**
     * 业务模块
     */
    private String moduleId;

    /**
     * 类型
     */
    private String type;

    /**
     * 用户类型
     */
    private String accountType;

    /**
     * 用户
     */
    private String accountId;

    /**
     * 用户名称
     */
    private String accountName;

    /**
     * 角色
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 数据编码
     */
    private String dataCodes;

    /**
     * 数据名称
     */
    private String dataNames;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String memo;

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
        return this.authId;
    }

}
