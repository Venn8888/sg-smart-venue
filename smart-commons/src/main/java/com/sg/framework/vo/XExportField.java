package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("field")
public class XExportField{
	@XStreamAlias("value")
	private String value;//编码
	@XStreamAlias("name")
	private String name;//名称
	@XStreamAlias("property")
	private String property;//属性
	@XStreamAlias("fieldName")
	private String fieldName;//字段
	@XStreamAlias("dataType")
	private String dataType;//数据类型
	
	@XStreamAlias("sn")
	private Long sn = 1l;//序号
	@XStreamAlias("status")
	private Boolean status = true;//状态
	@XStreamAlias("memo")
	private String memo;//说明

	@XStreamAlias("fieldType")
	private String fieldType;//编辑类型
	@XStreamAlias("data")
	private String data;//数据
	@XStreamAlias("formatter")
	private String formatter;//格式
	
	@XStreamAlias("isSum")
	private Boolean isSum = false;//是否统计

	public XExportField() {
		super();
	}
	public XExportField(XProperty xp) {
		this.property = xp.getValue();
		this.name = xp.getName();
		this.value = xp.getValue();
		this.fieldName = xp.getFieldName();
		this.dataType = xp.getDataType();
		this.fieldType = xp.getFieldType();
		this.status = xp.getStatus();

		this.formatter = xp.getFormatter();
		this.data = xp.getData();
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

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public Boolean getIsSum() {
		return isSum;
	}

	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}
}

