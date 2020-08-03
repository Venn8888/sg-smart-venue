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
 * 业务资源
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_authority")
public class AuthorityDomain extends Model<AuthorityDomain> {

    private static final long serialVersionUID=1L;

    /**
     * 业务资源
     */
    @TableId(value = "AUTHORITY_ID", type = IdType.AUTO)
    private String authorityId;

    /**
     * 业务系统
     */
    private String appId;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 类型
     */
    private String type;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类
     */
    private String category;

    /**
     * 业务模型
     */
    private String entityName;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 业务方法
     */
    private String methodName;

    /**
     * 权限代码
     */
    private String authCode;

    /**
     * URI
     */
    private String authUri;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 层次代码
     */
    private String levelNo;

    /**
     * 序号
     */
    private BigDecimal sn;

    /**
     * 是否审计
     */
    private String isAudit;

    /**
     * 是否认证
     */
    private String isAuth;

    /**
     * 是否开放
     */
    private String isOpen;

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
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;

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
        return this.authorityId;
    }

}
