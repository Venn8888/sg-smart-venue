package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.sg.framework.util.DataTypeUtil;
import com.sg.framework.util.StringUtil;

@XStreamAlias("column")
public class XColumn {
	@XStreamAlias("sqlType")
	private int sqlType;
	@XStreamAlias("sqlTypeName")
	private String sqlTypeName;
	@XStreamAlias("sqlName")
	private String sqlName;
	@XStreamAlias("isPk")
	private boolean isPk;
	@XStreamAlias("isFk")
	private boolean isFk;
	@XStreamAlias("size")
	private int size;
	@XStreamAlias("decimalDigits")
	private int decimalDigits;
	@XStreamAlias("isNullable")
	private boolean isNullable;
	@XStreamAlias("uniqueIndex")
	private String uniqueIndex;
	@XStreamAlias("defaultValue")
	private String defaultValue;
	@XStreamAlias("remarks")
	private String remarks;
	@XStreamAlias("columnAlias")
	private String columnAlias;
	@XStreamAlias("foreignKey")
	private String foreignKey;//外键
	@XStreamAlias("foreignTable")
	private String foreignTable;//关联表
	
//	@XStreamAlias("editType")
//	private String editType;//引用 类型
//	@XStreamAlias("formatter")
//	private String formatter;//格式
//	@XStreamAlias("data")
//	private String data;//引用数据
//	@XStreamAlias("operation")
//	private String operation;//查询类型
//	@XStreamAlias("isFilter")
//	private boolean isFilter;//是否查询
//	@XStreamAlias("required")
//	private boolean required;//是否必输
	@XStreamAlias("sn")
	private Long sn;//序号
	@XStreamAlias("status")
	private Boolean status = true;//状态
	
	public XColumn(int sqlType, String sqlTypeName, String sqlName,
			int size, int decimalDigits, boolean isPk, boolean isNullable,
			String uniqueIndex, String defaultValue,
			String remarks) {
		this.sqlType = sqlType;
		this.sqlTypeName = sqlTypeName;
		this.sqlName = sqlName;
		this.size = size;
		this.decimalDigits = decimalDigits;
		this.isPk = isPk;
		this.isNullable = isNullable;
		this.uniqueIndex = uniqueIndex;
//		this.isIndexed = isIndexed;
//		this.isUnique = isUnique;
		this.defaultValue = defaultValue;
		this.remarks = remarks;
		
		if(!StringUtil.isEmpty(remarks)){
			this.columnAlias = remarks.split(" ")[0];
		}
		
		String type = getJavaType();
//		if(isPk){
//			this.required = false;
//			this.editType = FieldType.HIDDEN;
//		}else{
//			this.required = true;
//			this.editType = FieldType.TEXT;
//			
//			if(DataTypesUtil.isDate(type)){
//				this.editType = FieldType.DATE;
//				this.formatter = "yyyy-MM-dd";
//			}
//			if(DataTypesUtil.isDateTime(type)){
//				this.editType = FieldType.DATETIME;
//				this.formatter = "yyyy-MM-dd Hi:mm:ss";
//			}
//			if(DataTypesUtil.isFloatNumber(type)){
//				this.editType = FieldType.NUMBER;
//			}
//			if(DataTypesUtil.isIntegerNumber(type)){
//				this.editType = FieldType.DIGITS;
//			}
//		}
	}
	
	public int getSqlType() {
		return sqlType;
	}
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
	
	public String getSqlTypeName() {
		return sqlTypeName;
	}
	public void setSqlTypeName(String sqlTypeName) {
		this.sqlTypeName = sqlTypeName;
	}
	
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	
	public boolean getIsPk() {
		return isPk;
	}
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
	
	public boolean isFk() {
		return isFk;
	}
	public void setFk(boolean isFk) {
		this.isFk = isFk;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	
	public boolean isNullable() {
		return isNullable;
	}
	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	public String getUniqueIndex() {
		return uniqueIndex;
	}

	public void setUniqueIndex(String uniqueIndex) {
		this.uniqueIndex = uniqueIndex;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getColumnAlias() {
		return columnAlias;
	}
	public void setColumnAlias(String columnAlias) {
		this.columnAlias = columnAlias;
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	public String getForeignTable() {
		return foreignTable;
	}

	public void setForeignTable(String foreignTable) {
		this.foreignTable = foreignTable;
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

	public String getPropertyName() {
		return StringUtil.toJavaVariableName(getSqlName());
	}

	public String getRelationName() {
		String name = getSqlName().substring(0, getSqlName().lastIndexOf("_"));
		return StringUtil.toJavaVariableName(name);
	}
	
	public String getJavaType() {
		return DataTypeUtil.getJavaType(getSqlType(), getSize(), getDecimalDigits());
	}
	
	public String getClassName(){
		return StringUtil.toJavaClassName(getSqlName());
	}
}
