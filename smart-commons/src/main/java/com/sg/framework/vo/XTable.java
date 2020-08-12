package com.sg.framework.vo;

import com.sg.framework.util.StringUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("table")
public class XTable {
	@XStreamAlias("sqlName")
	private String sqlName;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("remarks")
	private String remarks;
	@XStreamAlias("sequence")
	private String sequence;
	
	
	@XStreamAlias("columns")
	private List<XColumn> columns = new ArrayList<XColumn>();
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String tableAlias) {
		this.name = tableAlias;
	}
	public void setColumns(List<XColumn> columns) {
		this.columns = columns;
	}
	public List<XColumn> getColumns() {
		return columns;
	}
	public void setColumns(XColumn[] columns) {
		for (int i = 0; i < columns.length; ++i) {
			XColumn column = columns[i];
			this.columns.add(columns[i]);
		}
	}
	
	public void addColumn(XColumn column) {
		if (null != this.getColumns() && this.getColumns().size() > 0) {
			column.setSn(this.getColumns().get(this.getColumns().size() - 1).getSn() + 1);
		} else {
			column.setSn(1l);
		}
		columns.add(column);	
	}

	public String getClassName(String prefix){
		if(null == prefix){
			prefix = getSqlName().split("_")[0];
		}
		String name = StringUtil.removePrefix(getSqlName(), prefix.toUpperCase() + "_");
		return StringUtil.toJavaClassName(name);
	}
	
	
	public String toString() {
		return sqlName + "[" + name + "]";
	}
	
	public static void main(String[] args){
		System.out.println(StringUtil.toJavaClassName("DM_ORG_ID"));
	}
}
