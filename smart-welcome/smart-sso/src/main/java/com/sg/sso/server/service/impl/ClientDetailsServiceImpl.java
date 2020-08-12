package com.sg.sso.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: 
 * @date: 
 * @description:
 */
@Slf4j
@Service("clientDetailsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        try {
            BaseClientDetails baseClientDetails = (BaseClientDetails) jdbcClientDetailsService.loadClientByClientId(clientId);
            if (baseClientDetails == null) {
                return null;
            }
            //TODO

//            String appId = baseClientDetails.getAdditionalInformation().get("appId").toString();
//            baseClientDetails.setAuthorities(baseAuthorityService.findAuthorityByApp(appId));
            return baseClientDetails;
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
    }
}
