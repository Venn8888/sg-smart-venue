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
 * 权限对象
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dimension")
public class DimensionDomain extends Model<DimensionDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 权限对象
     */
    @TableId(value = "DIMENSION_ID", type = IdType.AUTO)
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
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 状态
     */
    private String status;

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
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

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
        return this.dimensionId;
    }

}
