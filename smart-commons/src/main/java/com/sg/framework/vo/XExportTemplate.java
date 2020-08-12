package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("x-export")
public class XExportTemplate {
	@XStreamAlias("moduleNo")
	private String moduleNo;// 业务模块
	@XStreamAlias("value")
	private String value;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("tableName")
	private String tableName;
	@XStreamAlias("domain")
	private String domain;
	@XStreamAlias("sqlClause")
	private String sqlClause;
	@XStreamAlias("whereClause")
	private String whereClause;
	@XStreamAlias("groupBy")
	private String groupBy;
	@XStreamAlias("sums")
	private String sums;

	@XStreamAlias("sortName")
	private String sortName;
	@XStreamAlias("sortOrder")
	private String sortOrder;

	@XStreamAlias("sn")
	private Long sn = 1l; // 序号
	@XStreamAlias("status")
	private Boolean status = true;	
	@XStreamAlias("memo")
	private String memo;

	@XStreamAlias("fields")
	private List<XExportField> fields = new ArrayList<XExportField>();
	@XStreamAlias("exports")
	private List<XExportTemplate> exports = new ArrayList<XExportTemplate>();
	
	public XExportTemplate() {
		super();
	}
	
	public XExportTemplate(String module, XDomainTemplate domain) {
		super();
		this.moduleNo = module;
		this.value = domain.getValue() + "_Out_Export";
		this.name = domain.getName();
		this.tableName = domain.getTableName();
		this.domain = domain.getClassName();
		this.sortName = "o.UPDATED_DATE";
		this.sortOrder = "desc";
		this.memo = domain.getName();

		for (XProperty property : domain.getProperties()) {
			XExportField column = new XExportField(property);
			this.getFields().add(column);
		}
	}
	
	public String getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getSqlClause() {
		return sqlClause;
	}
	public void setSqlClause(String sqlClause) {
		this.sqlClause = sqlClause;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getSums() {
		return sums;
	}
	public void setSums(String sums) {
		this.sums = sums;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Long getSn() {
		return sn;
	}
	public void setSn(Long sn) {
		this.sn = sn;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<XExportField> getFields() {
		return fields;
	}
	public void setFields(List<XExportField> fields) {
		this.fields = fields;
	}
	public List<XExportTemplate> getExports() {
		return exports;
	}
	public void setExports(List<XExportTemplate> exports) {
		this.exports = exports;
	}
}

