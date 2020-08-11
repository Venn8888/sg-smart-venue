package com.sg.gateway.server.service;


import com.sg.gateway.model.AuthorityResource;

import java.util.List;

/**
 * @author: xxxxxxx
 * @date: 2018/10/24 16:49
 * @description:
 */
public interface IBaseAuthorityService {
	public List<AuthorityResource> findAuthorityResource();
}
