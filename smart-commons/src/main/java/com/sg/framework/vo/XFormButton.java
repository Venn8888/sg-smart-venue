package com.sg.framework.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("button")
public class XFormButton {
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("value")
	private String value;//权限控制 格式:资源_操作
	@XStreamAlias("operation")
	private String operation;
	@XStreamAlias("sn")
	private Long sn;
	@XStreamAlias("onclick")
	private String onclick;
	@XStreamAlias("callback")
	private String callback;
	@XStreamAlias("icon")
	private String icon;
	@XStreamAlias("authCode")
	private String authCode;
	@XStreamAlias("status")
	private Boolean status = true;//状态
	@XStreamAlias("memo")
	private String memo;
	

	public XFormButton() {
		super();
	}
	
	public XFormButton(String module, String domain, String value, String name, Long sn) {
		this.value = value;
		this.name = name;
		this.sn = sn;
		this.icon = "menu";
		this.onclick = value + domain;
		this.authCode = module + ":" + domain + ":" + value;
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
	public Long getSn() {
		return sn;
	}
	public void setSn(Long sn) {
		this.sn = sn;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String cssClass) {
		this.icon = cssClass;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
}
