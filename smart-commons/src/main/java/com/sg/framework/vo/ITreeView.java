package com.sg.framework.vo;

/**
 * 是否树结构
 * @author Administrator
 *
 */
public interface ITreeView {
	public String getParentId();
	public void setParentId(String parentId);
	public String getLevelNo();
	public void setLevelNo(String levelNo);
	public Integer getSn();
	public void setSn(Integer sn);
	public ITreeView getParent();
//	public void setParent(ITreeView treeView);
}