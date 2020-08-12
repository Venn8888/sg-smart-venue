package com.sg.sso.server.service.impl;

import com.sg.system.facade.UserAccountFacade;
import com.sg.framework.constants.Constants.YesNo;
import com.sg.framework.security.BaseUserDetails;
import com.sg.framework.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * OAuth2用户信息获取实现类
 *
 * @author liwen
 */
@Slf4j
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String DEFAULT_SELECT_STATEMENT = "select o.ACCOUNT_ID,o.TYPE,o.USER_NAME,o.PASSWORD,o.DEFAULT_MAIN_ID,o.DEFAULT_ORG_ID,o.IS_ENABLE,o.IS_AUTHENTICATED,o.EXPIRED_DATE,o.PASSWORD_EXPIRED_TIME from SYS_USER_ACCOUNT o where o.IS_ACTIVE='Y' and o.USER_NAME = ?";
    private static final String DEFAULT_SELECT_DATA_ORG = "select o.ORG_ID from SYS_ACCOUNT_ORG o where o.IS_ACTIVE='Y' and o.ACCOUNT_ID = ?";


    private final JdbcTemplate jdbcTemplate;

    @Value("${application.common.client-id}")
    private String clientId;

    @Reference
    private UserAccountFacade userAccountFacade;

    public UserDetailsServiceImpl(DataSource dataSource) {
        Assert.notNull(dataSource, "DataSource required");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            BaseUserDetails ssoUser = this.jdbcTemplate.queryForObject(DEFAULT_SELECT_STATEMENT, new UserRowMapper(), username);

            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            Assert.notNull(ssoUser, "ssoUser is null");
            String ip = WebUtil.getHost(request);
            log.info("ip:{}", ip);
            Set<String> auths = userAccountFacade.authByAccountId(ssoUser.getAccountId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (String auth : auths) {
                if (StringUtils.isBlank(auth)) {
                    continue;
                }
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(auth);
                grantedAuthorities.add(grantedAuthority);
            }
            ssoUser.setAuthorities(grantedAuthorities);

            List<String> list = this.jdbcTemplate.queryForList(DEFAULT_SELECT_DATA_ORG, new Object[]{ssoUser.getAccountId()}, String.class);

            list.add((String) ssoUser.getAdditionInfos().get("dataOrgId"));
            ssoUser.getAdditionInfos().put("dataOrgIds", list);

            return ssoUser;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UsernameNotFoundException("No User with username: " + username);
        }
    }

    private static class UserRowMapper implements RowMapper<BaseUserDetails> {
        private UserRowMapper() {
        }

        @Override
        public BaseUserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            String id = rs.getString(1);
            String type = rs.getString(2);
            String username = rs.getString(3);
            String password = rs.getString(4);

            String mainId = rs.getString(5);
            String orgId = rs.getString(6);

            String isEnabel = rs.getString(7);
            String isAuthenticated = rs.getString(8);
            Date expiredDate = rs.getDate(9);
            Date passwordExpiredTime = rs.getDate(10);

            BaseUserDetails user = new BaseUserDetails();
            user.setAccountId(id);
            user.setUsername(username);
            user.setPassword(password);

            if (null != type) {
                user.setLoginType(type);
            }
            if (null != isEnabel) {
                boolean enabled = YesNo.YES.equals(isEnabel);
                user.setEnabled(enabled);
            }
            boolean accountNonLocked = true;
            if (null != isAuthenticated) {
                accountNonLocked = YesNo.YES.equals(isAuthenticated);
            }
            user.setAccountNonLocked(accountNonLocked);
            boolean credentialsNonExpired = true;
            if (null != passwordExpiredTime) {
                credentialsNonExpired = passwordExpiredTime.compareTo(new Date()) > 0;
            }
            user.setCredentialsNonExpired(credentialsNonExpired);
            boolean accountNonExpired = true;
            if (null != expiredDate) {
                accountNonExpired = expiredDate.compareTo(new Date()) > 0;
            }
            user.setAccountNonExpired(accountNonExpired);

            Map<String, Object> additionInfos = user.getAdditionInfos();

            if (null != mainId) {
                additionInfos.put("mainId", mainId);
            }
            if (null != orgId) {
                additionInfos.put("dataOrgId", orgId);
            }
            return user;
        }
    }
}
