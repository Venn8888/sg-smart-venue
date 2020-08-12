package com.sg.framework.vo;


public interface IWorkflow {
	public static String STATUS_INIT = "0";//草拟
	public static String STATUS_CHECKING = "1";//审核中
	public static String STATUS_CHECKED = "9";//审核完成
	public static String STATUS_BACK = "-1";//退回
	public static String STATUS_CANCEL = "-2";//作废取消
	
	public String getWfStatus();
	public void setWfStatus(String docStatus);
	
	public Long getRunId();
	public void setRunId(Long runId);
}
