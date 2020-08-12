package com.sg.gateway.spring.server.service.impl;

import com.sg.gateway.model.BaseApp;
import com.sg.gateway.spring.server.service.IBaseAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BaseAppServiceImpl implements IBaseAppService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    private final static String SELECT_APPS = "SELECT * FROM BASE_APP_INFO WHERE CLIENT_ID=? and is_active='Y' and status = 'Y'";

	@Override
    public BaseApp getApp(String appId) {
		 List<BaseApp> list = jdbcTemplate.query(SELECT_APPS, new Object[] {appId}, new RowMapper<BaseApp>() {
             @Override
             public BaseApp mapRow(ResultSet rs, int i) throws SQLException {
            	 BaseApp result = new BaseApp();
                 result.setAppId(rs.getString("APP_ID"));
                 result.setAppKey(rs.getString("CLIENT_ID"));
                 result.setSecretKey(rs.getString("CLIENT_SECRET"));
                 result.setAppName(rs.getString("NAME"));
                 result.setStatus(rs.getString("STATUS"));
                 return result;
             }
         });
		 if(null != list && !list.isEmpty()) {
			 return list.get(0);
		 }
		
		 return null;
	}

}
