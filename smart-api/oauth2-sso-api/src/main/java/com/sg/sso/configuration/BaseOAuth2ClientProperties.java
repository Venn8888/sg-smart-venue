package com.sg.sso.configuration;

import com.sg.common.client.OAuth2ClientDetails;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: 
 * @date: 
 * @description:
 */
@ConfigurationProperties(prefix = "application.client")
@Component
public class BaseOAuth2ClientProperties {
    private Map<String, OAuth2ClientDetails> oauth2;

    public Map<String, OAuth2ClientDetails> getOauth2() {
        return oauth2;
    }

    public void setOauth2(Map<String, OAuth2ClientDetails> oauth2) {
        this.oauth2 = oauth2;
    }
}
