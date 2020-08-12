package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("field")
public class XFormField {
	@XStreamAlias("name")
	private String name;//名称
	@XStreamAlias("value")
	private String value;//编码
	@XStreamAlias("property")
	private String property;//属性
	@XStreamAlias("refProperty")	
	private String refProperty;//关联属性
	@XStreamAlias("dataType")
	private String dataType;//数据类型
	@XStreamAlias("isPk")
	private Boolean isPk;
	
	@XStreamAlias("sn")
	private Long sn = 1l;//序号
	@XStreamAlias("status")
	private Boolean status = true;//状态
	@XStreamAlias("memo")
	private String memo;//说明
	@XStreamAlias("fieldType")
	private String fieldType;//编辑类型
	
	@XStreamAlias("formatter")
	private String formatter;//格式
	@XStreamAlias("data")
	private String data;//数据
	@XStreamAlias("link")
	private String link;//链接
	@XStreamAlias("defaultValue")
	private String defaultValue;//默认值	
	
	@XStreamAlias("size")
	private Long size;//长度
	@XStreamAlias("validation")
	private String validation;//数据验证
	@XStreamAlias("cols")
	private Long cols = 1l;//宽度
	@XStreamAlias("rows")
	private Long rows =1l;//高度	
	@XStreamAlias("required")
	private Boolean required = true;//是否必输
	@XStreamAlias("readonly")
	private Boolean readonly = false;//是否只读

	@XStreamAlias("align")
	private String align;//对齐方式
	@XStreamAlias("isSum")
	private Boolean isSum = false;//是否统计
	@XStreamAlias("sortable")
	private Boolean sortable = true;//是否排序
	
	public XFormField() {
		super();
	}
	public XFormField(XProperty xp) {
		this.property = xp.getValue();
		this.name = xp.getName();
		this.value = xp.getValue();
		this.dataType = xp.getDataType();
		this.size = Long.valueOf(xp.getLehgth());
		this.sn = xp.getSn();
		this.status = xp.getStatus();
		this.isPk = xp.getIsPk();

		this.fieldType = xp.getFieldType();
		this.formatter = xp.getFormatter();
		this.data = xp.getData();
//		this.required = xp.isRequired();
		if("date".equals(this.fieldType)
			||"digits".equals(this.fieldType)
			||"number".equals(this.fieldType)){
			this.validation = this.fieldType;
		}
		
		if(xp.getIsPk()){
			this.sortable = false;
		}else{
			this.sortable = true;
		}
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
	public Boolean getIsPk() {
		return isPk;
	}
	public void setIsPk(Boolean isPk) {
		this.isPk = isPk;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
	public Long getCols() {
		return cols;
	}
	public void setCols(Long cols) {
		this.cols = cols;
	}
	public Long getRows() {
		return rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public Boolean getReadonly() {
		return readonly;
	}
	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Boolean getIsSum() {
		return isSum;
	}
	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}
	public Boolean getSortable() {
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}
	
	
}
