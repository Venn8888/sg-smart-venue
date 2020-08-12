package com.sg.framework.vo;

import com.sg.framework.util.StringUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("domain")
public class XDomainTemplate {
	@XStreamAlias("moduleNo")
	private String moduleNo;//业务模块
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("className")
	private String className;
	@XStreamAlias("tableName")
	private String tableName;
	@XStreamAlias("value")
	private String value;
	
	@XStreamAlias("idGenerator")
	private String idGenerator;
	@XStreamAlias("parentDomain")
	private String parentDomain;
	
	@XStreamAlias("hasAttach")
	private boolean hasAttach = false;
	@XStreamAlias("hasTree")
	private boolean hasTree = false;
	@XStreamAlias("isWfForm")
	private boolean isWfForm = false;//是否流程控制
	@XStreamAlias("isApp")
	private boolean isApp = false;//是否应用控制
	@XStreamAlias("isMain")
	private boolean isMain = true;//是否业务主体
	
	@XStreamAlias("properties")
	private List<XProperty> properties = new ArrayList<XProperty>();
	@XStreamAlias("relations")
	private List<XRelation> relations = new ArrayList<XRelation>();
	@XStreamOmitField
	private XProperty pk;
	
	public XDomainTemplate(){
		super();
	}
	
	public XDomainTemplate(String pkg, String module, XTable table){
		super();
		String value = StringUtil.getTableClassName(table.getSqlName());
		String className = pkg + ".entity." + value;
		
		this.value = value;
		this.tableName = table.getSqlName();
		this.className = className;
		this.name = table.getName();
		this.moduleNo = module;
		
		for(XColumn column : table.getColumns()) {
			XProperty property = new XProperty(column);
			this.getProperties().add(property);
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getIdGenerator() {
		return idGenerator;
	}

	public void setIdGenerator(String idGenerator) {
		this.idGenerator = idGenerator;
	}

	public String getParentDomain() {
		return parentDomain;
	}

	public void setParentDomain(String parentDomain) {
		this.parentDomain = parentDomain;
	}

	public boolean getHasAttach() {
		return hasAttach;
	}

	public void setHasAttach(boolean hasAttachment) {
		this.hasAttach = hasAttachment;
	}

	public boolean getHasTree() {
		return hasTree;
	}

	public void setHasTree(boolean hasTree) {
		this.hasTree = hasTree;
	}

	public boolean getIsWfForm() {
		return isWfForm;
	}

	public void setIsWfForm(boolean isWfForm) {
		this.isWfForm = isWfForm;
	}

	public boolean getIsApp() {
		return isApp;
	}

	public void setIsApp(boolean isApp) {
		this.isApp = isApp;
	}

	public boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(boolean isMain) {
		this.isMain = isMain;
	}


	public List<XProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<XProperty> properties) {
		this.properties = properties;
	}
	public List<XRelation> getRelations() {
		return relations;
	}
	public void setRelations(List<XRelation> relations) {
		this.relations = relations;
	}

	public XProperty getPk() {
		if(null == pk && null != properties) {
			for(XProperty p : properties) {
				if(p.getIsPk()) {
					pk = p;
					break;
				}
			}
		}
		return pk;
	}

	public void setPk(XProperty pk) {
		this.pk = pk;
	}

}
