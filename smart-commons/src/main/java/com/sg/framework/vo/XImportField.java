package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("field")
public class XImportField{
	@XStreamAlias("name")
	private String name;//名称
	@XStreamAlias("value")
	private String value;//编码
	@XStreamAlias("property")
	private String property;//属性
	@XStreamAlias("fieldName")
	private String fieldName;//字段
	
	@XStreamAlias("refProperty")
	private String refProperty;//关联属性
	@XStreamAlias("dataType")
	private String dataType;//数据类型
	
	@XStreamAlias("sn")
	private Long sn = 1l;//序号
	@XStreamAlias("status")
	private Boolean status = false;//状态
	@XStreamAlias("memo")
	private String memo;//说明
	
	@XStreamAlias("fieldType")
	private String fieldType;//编辑类型
	@XStreamAlias("formatter")
	private String formatter;//格式
	@XStreamAlias("data")
	private String data;//数据
	@XStreamAlias("defaultValue")
	private String defaultValue;//默认值	
	
	@XStreamAlias("size")
	private Long size;//长度
	@XStreamAlias("validation")
	private String validation;//数据验证
	@XStreamAlias("required")
	private Boolean required = false;//是否必输
	
	public XImportField() {
		super();
	}
	public XImportField(XProperty xp) {
		this.property = xp.getValue();
		this.fieldName = xp.getFieldName();
		this.name = xp.getName();
		this.value = xp.getValue();
		this.dataType = xp.getDataType();
		this.size = Long.valueOf(xp.getLehgth());
		this.status = xp.getStatus();

		this.fieldType = xp.getFieldType();
		this.formatter = xp.getFormatter();
		this.data = xp.getData();
//		this.required = xp.isRequired();
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
	public String getRefProperty() {
		return refProperty;
	}
	public void setRefProperty(String refProperty) {
		this.refProperty = refProperty;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
}

