package com.sg.framework.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("x-import")
public class XImportTemplate{
	@XStreamAlias("moduleNo")
	private String moduleNo;// 业务模块
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("value")
	private String value;
	@XStreamAlias("tableName")
	private String tableName;
	@XStreamAlias("domain")
	private String domain;

	@XStreamAlias("sn")
	private Long sn = 1l; // 序号
	@XStreamAlias("status")
	private Boolean status = true;	
	@XStreamAlias("memo")
	private String memo;

	@XStreamAlias("fields")
	private List<XImportField> fields = new ArrayList<XImportField>();

	public XImportTemplate() {
		super();
	}
	
	public XImportTemplate(String module, XDomainTemplate domain) {
		super();
		this.moduleNo = module;
		this.value = domain.getValue() + "_In_Import";
		this.name = domain.getName();
		this.domain = domain.getClassName();
		this.memo = domain.getName();

		for (XProperty property : domain.getProperties()) {
			XImportField column = new XImportField(property);
			this.getFields().add(column);
		}
	}
	
	public String getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public List<XImportField> getFields() {
		return fields;
	}

	public void setFields(List<XImportField> fields) {
		this.fields = fields;
	}
}

