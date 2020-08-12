package com.sg.framework.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

/**
 * 自定义认证用户信息
 *
 * 
 */
public class BaseUserDetails implements UserDetails {
    private static final long serialVersionUID = -123308657146774881L;
    /**
     * 账户Id
     */
    private String accountId;

    /***
     * 登录类型
     */
    private String loginType;
    /**
     * 登录名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 是否已锁定
     */
    private boolean accountNonLocked;
    /**
     * 是否已过期
     */
    private boolean accountNonExpired;
    /**
     * 密码是否已过期
     */
    private boolean credentialsNonExpired;
    
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;
    
    private String clientId;
//    private String openId;

    /**
     * 用户权限
     */
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 用户附加属性
     */
    private Map<String, Object> additionInfos;


    @JsonIgnore
    @JSONField(serialize = false)
    
    public String getPassword() {
        return this.password;
    }

    
    public String getUsername() {
        return this.username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String type) {
        this.loginType = type;
    }

	
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

//	public String getDomain() {
//		return domain;
//	}
//
//	public void setDomain(String domain) {
//		this.domain = domain;
//	}
//
//	public String getOpenId() {
//		return openId;
//	}
//
//	public void setOpenId(String openId) {
//		this.openId = openId;
//	}

	public Map<String, Object> getAdditionInfos() {
        if (additionInfos == null) {
            additionInfos = Maps.newHashMap();
        }
        return additionInfos;
    }

    public void setAdditionInfos(Map<String, Object> additionInfos) {
        this.additionInfos = additionInfos;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
        	authorities = Collections.EMPTY_LIST;
        }
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
