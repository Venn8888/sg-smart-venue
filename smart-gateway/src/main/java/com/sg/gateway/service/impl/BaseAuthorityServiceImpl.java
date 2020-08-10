package com.sg.gateway.service.impl;

import com.sg.gateway.model.AuthorityResource;
import com.sg.gateway.service.IBaseAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BaseAuthorityServiceImpl implements IBaseAuthorityService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    private final static String SELECT_AUTHORITY = "SELECT " + 
    		"		api.AUTHORITY_ID, " + 
    		"		api.AUTH_CODE, " + 
    		"		api.AUTH_URI, " + 
    		"		api.SERVICE_ID, " + 
    		"		api.IS_AUTH, " + 
    		"		api.IS_OPEN, " + 
    		"		api.STATUS, " + 
    		"		route.path AS PREFIX " + 
    		"	FROM sys_authority AS api " + 
    		"	LEFT JOIN API_ROUTE_RULE AS route ON route.service_id = api.service_id";

	@Override
	public List<AuthorityResource> findAuthorityResource() {
		List<AuthorityResource> list = jdbcTemplate.query(SELECT_AUTHORITY, new RowMapper<AuthorityResource>() {
            @Override
            public AuthorityResource mapRow(ResultSet rs, int i) throws SQLException {
            	AuthorityResource result = new AuthorityResource();
                result.setAuthorityId(rs.getString("AUTHORITY_ID"));
                result.setAuthority(rs.getString("AUTH_CODE"));
                result.setPath(rs.getString("AUTH_URI"));
                result.setServiceId(rs.getString("SERVICE_ID"));
                result.setIsAuth(rs.getString("IS_AUTH"));
                result.setIsOpen(rs.getString("IS_OPEN"));
                result.setStatus(rs.getString("STATUS"));
                result.setPrefix(rs.getString("PREFIX"));
                return result;
            }
        });
		return list;
	}

}
