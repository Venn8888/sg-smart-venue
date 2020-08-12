package com.sg.framework.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.sg.framework.util.StringUtil;

@XStreamAlias("form")
public class XFormTemplate {
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

	@XStreamAlias("action")
	private String action;
	@XStreamAlias("script")
	private String script; // 脚本
	
	@XStreamAlias("property")
	private String property;

	@XStreamAlias("sortName")
	private String sortName;
	@XStreamAlias("sortOrder")
	private String sortOrder;

	@XStreamAlias("isSum")
	private Boolean isSum = false;//是否统计
	@XStreamAlias("sn")
	private Long sn = 1l; // 序号
	@XStreamAlias("status")
	private String status = "Y";	
	@XStreamAlias("memo")
	private String memo;

	@XStreamAlias("forms")
	private List<XFormTemplate> forms = new ArrayList<XFormTemplate>();

	@XStreamAlias("fields")
	private List<XFormField> fields = new ArrayList<XFormField>();
	@XStreamAlias("buttons")
	private List<XFormButton> buttons = new ArrayList<XFormButton>();	

	public XFormTemplate() {
		super();
	}

	public XFormTemplate(String module, XDomainTemplate domain) {
		super();
		this.moduleNo = module;
		this.value = domain.getValue() + "_Form";
		this.name = domain.getName();
		this.domain = domain.getClassName();
		this.property = StringUtil.uncapitalize(domain.getValue());
		this.memo = domain.getName();

		for (XProperty property : domain.getProperties()) {
			XFormField column = new XFormField(property);
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
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

	public Boolean getIsSum() {
		return isSum;
	}

	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<XFormTemplate> getForms() {
		return forms;
	}

	public void setForms(List<XFormTemplate> forms) {
		this.forms = forms;
	}

	public List<XFormField> getFields() {
		return fields;
	}

	public void setFields(List<XFormField> fields) {
		this.fields = fields;
	}

	public List<XFormButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<XFormButton> buttons) {
		this.buttons = buttons;
	}
}
