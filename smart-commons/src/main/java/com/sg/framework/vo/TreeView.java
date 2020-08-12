package com.sg.framework.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeView implements Serializable{
	private String id;
	private String parentId;
	private String text;
	private String value;
	private boolean showcheck = true;
//	private String checkstate;
	private boolean hasChildren = true;
	private boolean isexpand = true;
//	private boolean complete = false;
	private List<TreeView> ChildNodes;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getShowcheck() {
		return this.showcheck;
	}

	public void setShowcheck(boolean showcheck) {
		this.showcheck = showcheck;
	}

//	@JSON(serialize=false)
//	public String getCheckstate() {
//		return this.checkstate;
//	}
//
//	public void setCheckstate(String checkstate) {
//		this.checkstate = checkstate;
//	}

	public boolean getHasChildren() {
		return this.hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public boolean getIsexpand() {
		return this.isexpand;
	}

	public void setIsexpand(boolean isexpand) {
		this.isexpand = isexpand;
	}

//	@JSON(serialize=false)
//	public boolean getComplete() {
//		return this.complete;
//	}
//
//	public void setComplete(boolean complete) {
//		this.complete = complete;
//	}

	public List<TreeView> getChildNodes() {
		return this.ChildNodes;
	}

	public void setChildNodes(List<TreeView> ChildNodes) {
		this.ChildNodes = ChildNodes;
	}
	
	public void addChildNodes(TreeView childNode) {
		if(null == this.ChildNodes){
			ChildNodes = new ArrayList<TreeView>();
		}
		this.ChildNodes.add(childNode);
	}
}