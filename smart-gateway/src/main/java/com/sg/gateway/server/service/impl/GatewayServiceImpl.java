package com.sg.gateway.server.service.impl;

import com.sg.gateway.model.IpLimitApi;
import com.sg.gateway.server.service.IGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class GatewayServiceImpl implements IGatewayService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    private final static String SELECT_IP_LIMIT_API = "SELECT" + 
    		"    i.policy_id," + 
    		"    i.api_id," + 
    		"    p.name," + 
    		"    p.type," + 
    		"    i.api_code," + 
    		"    i.api_name," + 
    		"    i.api_category," + 
    		"    i.service_id," + 
    		"    i.auth_uri," + 
    		"    p.data_value" + 
    		"    FROM API_API_IP_LIMIT AS i" + 
    		"    INNER JOIN API_IP_LIMIT AS p ON i.policy_id = p.policy_id" + 
    		"    where p.type = ? ";

	@Override
    public List<IpLimitApi> getApiBlackList() {
		List<IpLimitApi> list = jdbcTemplate.query(SELECT_IP_LIMIT_API, new Object[] {"0"}, new IpLimitApiRowMapper());
		return list;
	}

	@Override
    public List<IpLimitApi> getApiWhiteList() {
		List<IpLimitApi> list = jdbcTemplate.query(SELECT_IP_LIMIT_API, new Object[] {"1"}, new IpLimitApiRowMapper());
		return list;
	}

	class IpLimitApiRowMapper implements RowMapper<IpLimitApi> {
		@Override
        public IpLimitApi mapRow(ResultSet rs, int i) throws SQLException {
        	IpLimitApi result = new IpLimitApi();
            result.setPolicyId(rs.getString("policy_id"));
            result.setApiId(rs.getString("api_id"));
            result.setPolicyName(rs.getString("name"));
            result.setPolicyType(rs.getString("type"));
            result.setApiCode(rs.getString("api_code"));
            result.setApiName(rs.getString("api_name"));
            result.setApiCategory(rs.getString("api_category"));
            result.setServiceId(rs.getString("service_id"));
            result.setPath(rs.getString("auth_uri"));
            result.setIpAddress(rs.getString("data_value"));
            return result;
        }
	}
}
