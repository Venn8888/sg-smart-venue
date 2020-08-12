package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("relation")
public class XRelation {
	@XStreamAlias("type")
	private String type;
	@XStreamAlias("tableName")
	private String tableName;
	@XStreamAlias("fieldName")
	private String fieldName;
	@XStreamAlias("refFieldName")
	private String refFieldName;
	@XStreamAlias("refName")
	private String refName; //关联对象
	@XStreamAlias("refDomain")
	private String refDomain;//关联对象名称
	@XStreamAlias("whereClause")
	private String whereClause;//关联对象名称
	@XStreamAlias("memo")
	private String memo;//关联对象名称
	
	@XStreamAlias("sn")
	private Long sn;
	@XStreamAlias("status")
	private Boolean status = true;//状态
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getRefFieldName() {
		return refFieldName;
	}
	public void setRefFieldName(String refFieldName) {
		this.refFieldName = refFieldName;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getRefDomain() {
		return refDomain;
	}
	public void setRefDomain(String refDomain) {
		this.refDomain = refDomain;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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

}
