package com.sg.framework.util;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
	public static boolean isOracleDataBase(Connection conn) {
		return isOracleDataBase(getMetaData(conn));
	}

	public static boolean isOracleDataBase(DatabaseMetaData metadata) {
		try {
			boolean ret = false;
			ret = (metadata.getDatabaseProductName().toLowerCase()
						.indexOf("oracle") != -1);
			return ret;
		}catch(SQLException s) {
			return false;
//			throw new RuntimeException(s);
		}
	}
	public static boolean isHsqlDataBase(DatabaseMetaData metadata) {
		try {
			boolean ret = false;
			ret = (metadata.getDatabaseProductName().toLowerCase()
						.indexOf("hsql") != -1);
			return ret;
		}catch(SQLException s) {
			return false;
//			throw new RuntimeException(s);
		}
	}		

	public static DatabaseMetaData getMetaData(Connection conn) {
		try {
			return conn.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				//ignore
			}
		}
	}
	
	public static void close(PreparedStatement s) {
		if(s != null) {
			try {
				s.close();
			}catch(Exception e) {
				//ignore
			}
		}
	}
	
	public static void close(Statement s) {
		if(s != null) {
			try {
				s.close();
			}catch(Exception e) {
				//ignore
			}
		}
	}
	
	public static void close(ResultSet s) {
		if(s != null) {
			try {
				s.close();
			}catch(Exception e) {
				//ignore
			}
		}
	}
	
	public static void close(Connection conn,ResultSet rs) {
		close(conn);
		close(rs);
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) {
		close(conn);
		close(ps);
		close(rs);
	}

	public static void close(PreparedStatement ps,ResultSet rs) {
		close(ps);
		close(rs);
	}
	
	public static void close(Connection conn,Statement s,ResultSet rs) {
		close(conn);
		close(s);
		close(rs);
	}

	public static void close(Statement s,ResultSet rs) {
		close(s);
		close(rs);
	}
	
	public static String queryForString(Connection conn, String sql) {
		Statement s = null;
		ResultSet rs = null;
		try {
			s =  conn.createStatement();
			rs = s.executeQuery(sql);
			if(rs.next()) {
				return rs.getString(1);
			}
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DatabaseUtil.close(null,s,rs);
		}
	}
}
