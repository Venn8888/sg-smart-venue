package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("column")
public class XGridColumn {
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
	private Long sn;//序号
	@XStreamAlias("status")
	private Boolean status = true;//状态
	@XStreamAlias("memo")
	private String memo;//说明

	@XStreamAlias("fieldType")
	private String fieldType;//显示类型
	@XStreamAlias("formatter")
	private String formatter;//格式
	@XStreamAlias("data")
	private String data;//数据
	@XStreamAlias("link")
	private String link;//链接
	@XStreamAlias("width")
	private Long width = 1l;//宽度

	@XStreamAlias("align")
	private String align = "left";//对齐方式
	@XStreamAlias("isPk")
	private boolean isPk;
	@XStreamAlias("sortable")
	private Boolean sortable = true;//是否排序
	@XStreamAlias("isSum")
	private Boolean isSum = false;//是否统计

	public XGridColumn() {
		super();
	}
	public XGridColumn(XProperty xp) {
		super();
		this.name = xp.getName();
		this.value = xp.getValue();
		this.dataType = xp.getDataType();
		this.fieldName = "o." + xp.getFieldName();
		this.property = "o." + xp.getValue();
		this.sn = xp.getSn();
		this.status = xp.getStatus();
		this.isPk = xp.getIsPk();

		this.fieldType = xp.getFieldType();
		this.formatter = xp.getFormatter();
		this.data = xp.getData();
		if(xp.getIsPk()){
			this.sortable = false;
		}else{
			this.sortable = true;
		}
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

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getSn() {
		return sn;
	}
	public void setSn(Long sn) {
		this.sn = sn;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Long getWidth() {
		return width;
	}
	public void setWidth(Long width) {
		this.width = width;
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
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public boolean getIsPk() {
		return isPk;
	}
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
}
