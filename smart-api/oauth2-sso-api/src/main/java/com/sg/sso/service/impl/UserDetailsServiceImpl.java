package com.sg.sso.service.impl;

import com.sg.sso.utils.WebUtil;
import com.sg.system.domain.UserAccountDomain;
import com.sg.system.facade.UserAccountFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * OAuth2用户信息获取实现类
 *
 * @author liwen
 */
@Slf4j
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${application.common.client-id}")
    private String clientId;

    @Reference
    private UserAccountFacade userAccountFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            UserAccountDomain domain = userAccountFacade.getByUserName(username);
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            Assert.notNull(domain, "ssoUser is null");
            String ip = WebUtil.getHost(request);
            log.info("ip:{}", ip);

            Set<String> authS = userAccountFacade.authsbyaccountid(domain.getAccountId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (String auth : authS) {
                if (StringUtils.isBlank(auth)) {
                    continue;
                }
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(auth);
                grantedAuthorities.add(grantedAuthority);
            }
            User user = new User(username, domain.getPassword(), grantedAuthorities);

            return user;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UsernameNotFoundException("No User with username: " + username);
        }
    }

}
