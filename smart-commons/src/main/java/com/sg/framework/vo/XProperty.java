package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.sg.framework.util.DataTypeUtil;
import com.sg.framework.util.StringUtil;

@XStreamAlias("property")
public class XProperty {
	public static final String HIDDEN = "hidden"; //隐藏控件
	public static final String TEXT = "text"; //文本
	public static final String TEXTAREA = "textarea"; //多行文本
	public static final String SELECT = "select"; //字典列表
	public static final String LIST = "list"; //引用列表
	public static final String DATE = "date"; //日期控件
	public static final String DATETIME = "datetime"; //日期时间控件
	public static final String TIME = "time"; //时间控件
	public static final String DIGITS = "digits"; //整数
	public static final String NUMBER = "number"; //浮点数
	public static final String TREE = "tree"; //树
	public static final String GRID = "grid"; //列表
	public static final String AUTOCOMPLETE = "autocomplete"; //自动完成
	public static final String FILE = "file"; //操作
	public static final String ACTION = "action"; //操作
	
	@XStreamAlias("value")
	private String value;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("dataType")
	private String dataType;
	@XStreamAlias("fieldName")
	private String fieldName;
	
	@XStreamAlias("isPk")
	private boolean isPk;
	@XStreamAlias("uniqueIndex")
	private String uniqueIndex;
	@XStreamAlias("sequenceNo")
	private String sequenceNo;
	@XStreamAlias("isVirtual")
	private boolean isVirtual;
	@XStreamAlias("isAttachment")
	private boolean isAttachment;
	
	@XStreamAlias("length")
	private int lehgth;
	@XStreamAlias("defaultValue")
	private String defaultValue;
	
//	@XStreamAlias("required")
//	private boolean required;//是否必输
	@XStreamAlias("fieldType")
	private String fieldType;//字段类型
	@XStreamAlias("data")
	private String data;//引用数据
	@XStreamAlias("formatter")
	private String formatter;//格式
//	@XStreamAlias("isFilter")
//	private boolean isFilter;//是否查询
//	@XStreamAlias("operation")
//	private String operation;//查询类型
	
	@XStreamAlias("sn")
	private Long sn;
	@XStreamAlias("status")
	private Boolean status = true;//状态
	@XStreamAlias("memo")
	private String memo;//备注
	
	public XProperty() {
		super();
	}
	
	public XProperty(XColumn column) {
		super();
		this.value = column.getPropertyName();
		this.name = column.getColumnAlias();
		this.dataType = column.getJavaType();
		this.fieldName = column.getSqlName();
		this.isPk = column.getIsPk();
		this.uniqueIndex = column.getUniqueIndex();
		this.lehgth = column.getSize();
		//this.sn = column.getSn();
		this.status = column.getStatus();
		if(StringUtil.isNotEmpty(column.getDefaultValue())){
			this.defaultValue = column.getDefaultValue().replace("'", "");
		}
		this.memo = column.getRemarks();
		
		if(column.getIsPk()){
			this.fieldType = HIDDEN;
		}else{
			this.fieldType = TEXT;
			
			if(DataTypeUtil.isDate(column.getJavaType())){
				this.fieldType = DATE;
				this.formatter = "yyyy-MM-dd";
			}
			if(DataTypeUtil.isDateTime(column.getJavaType())){
				this.fieldType = DATETIME;
				this.formatter = "yyyy-MM-dd Hi:mm:ss";
			}
			if(DataTypeUtil.isFloatNumber(column.getJavaType())){
				this.fieldType = NUMBER;
			}
			if(DataTypeUtil.isIntegerNumber(column.getJavaType())){
				this.fieldType = DIGITS;
			}
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
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getUniqueIndex() {
		return uniqueIndex;
	}

	public void setUniqueIndex(String uniqueIndex) {
		this.uniqueIndex = uniqueIndex;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public boolean getIsVirtual() {
		return isVirtual;
	}

	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public boolean getIsPk() {
		return isPk;
	}

	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

	public boolean getIsAttachment() {
		return isAttachment;
	}
	public void setAttachment(boolean isAttachment) {
		this.isAttachment = isAttachment;
	}
	public int getLehgth() {
		return lehgth;
	}

	public void setLehgth(int lehgth) {
		this.lehgth = lehgth;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String editType) {
		this.fieldType = editType;
	}
}
