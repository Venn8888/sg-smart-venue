package com.sg.framework.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sg.framework.exception.BusinessException;
import com.sg.framework.vo.XColumn;
import com.sg.framework.vo.XTable;

/**
 * 
 * 根据数据库表的元数据(metadata)创建Table对象
 * 
 * <pre>
 * getTable(sqlName) : 根据数据库表�?,得到table对象
 * getAllTable() : 搜索数据库的�?有表,并得到table对象列表
 * </pre>
 */
public class DBMetaDataHelper {
	private static DBMetaDataHelper instance = null;
	private DatabaseMetaData databaseMetaData;
	private Connection connection;
	
	public static String defaultColumn = ",IS_ACTIVE,CREATED_DATE,UPDATED_DATE,CREATED_BY,UPDATED_BY,VERSION,MAIN_ID,DATA_ORG_ID,";

	private DBMetaDataHelper(Connection connection) throws Exception {
		this.connection = connection;
		databaseMetaData = DatabaseUtil.getMetaData(connection);
	}

	public synchronized static DBMetaDataHelper getInstance(Connection connection) throws Exception {
		if (instance == null)
			instance = new DBMetaDataHelper(connection);
		return instance;
	}

	public String getCatalog() {
		return null;
	}

	public String getSchema() {
		try {
			String schemal = databaseMetaData.getUserName();
	
			if (!StringUtil.isEmpty(schemal)) {
				return schemal.toUpperCase();
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	public List<XTable> getAllTables(String name) {
		try {
			String[] t = { "TABLE", "VIEW" };

			if(null == databaseMetaData){
				databaseMetaData = DatabaseUtil.getMetaData(connection);
			}
			ResultSet rs = databaseMetaData.getTables(null, getSchema(), StringUtil.upperCase(name) + "%", t);
			try {
				List<XTable> tables = new ArrayList<XTable>();
				while (rs.next()) {
					tables.add(createTable(rs));
				}
				return tables;
			} finally {
				DatabaseUtil.close(rs);
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	private XTable getTable(String schema, String tableName) {
		return getTable(getCatalog(), schema, tableName);
	}

	private XTable getTable(String catalog, String schema,
			String tableName) {
		XTable t = null;
		try {
			t = _getTable(catalog, schema, tableName);
			if (t == null && !tableName.equals(tableName.toUpperCase())) {
				t = _getTable(catalog, schema, tableName.toUpperCase());
			}
			if (t == null && !tableName.equals(tableName.toLowerCase())) {
				t = _getTable(catalog, schema, tableName.toLowerCase());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (t == null) {
			throw new NotFoundTableException("not found table with give name:"
					+ tableName);
		}
		return t;
	}

	public class NotFoundTableException extends RuntimeException {
		private static final long serialVersionUID = 5976869128012158628L;

		public NotFoundTableException(String message) {
			super(message);
		}
	}

	private XTable _getTable(String catalog, String schema,
			String tableName) throws SQLException {
		if (tableName == null || tableName.trim().length() == 0)
			throw new IllegalArgumentException("tableName must be not empty");
		catalog = StringUtil.defaultIfEmpty(catalog, null);
		schema = StringUtil.defaultIfEmpty(schema, null);

		//DatabaseMetaData dbMetaData = conn.getMetaData();
		ResultSet rs = databaseMetaData.getTables(catalog, schema, tableName, null);
		try {
			while (rs.next()) {
				XTable table = createTable(rs);
				return table;
			}
		} finally {
			DatabaseUtil.close(rs);
		}
		return null;
	}

	private XTable createTable(ResultSet rs)
			throws SQLException {
		String tableName = null;
		try {
			tableName = rs.getString("TABLE_NAME");
			System.out.println(tableName);
			String remarks = rs.getString("REMARKS");
			if (remarks == null && DatabaseUtil.isOracleDataBase(databaseMetaData)) {
				remarks = getOracleTableComments(tableName);
			}

			XTable table = new XTable();
			table.setSqlName(tableName);
			table.setSequence("SEQ_" + tableName);
			table.setRemarks(remarks);
			
			if (!StringUtil.isEmpty(remarks)) {
				table.setName(remarks);
			}

			retriveTableColumns(table);
			return table;
		} catch (SQLException e) {
			throw new RuntimeException("create table object error,tableName:"
					+ tableName, e);
		}
	}

	private void retriveTableColumns(XTable table)
			throws SQLException {
		List columns = getTableColumns(table.getSqlName());

		for (Iterator i = columns.iterator(); i.hasNext();) {
			XColumn column = (XColumn) i.next();
			table.addColumn(column);
		}
	}


	private List getTableColumns(String tableName)
			throws SQLException {
		List primaryKeys = getTablePrimaryKeys(tableName);
		Map<String, ForeignKey> foreignKeys = getImportedKeys(tableName, getCatalog(), getSchema());

//		List indices = new LinkedList();
		Map uniqueIndices = new HashMap();
//		Map uniqueColumns = new HashMap();
		ResultSet indexRs = databaseMetaData.getIndexInfo(getCatalog(), getSchema(), tableName, false, true);
		ResultSet columnRs = getColumnsResultSet(tableName);
		
		List columns = new LinkedList();
		try {
			while (indexRs.next()) {
				String columnName = indexRs.getString("COLUMN_NAME");
//				if (columnName != null) {
//					indices.add(columnName);
//				}

				// now look for unique columns
				String indexName = indexRs.getString("INDEX_NAME");
				boolean nonUnique = indexRs.getBoolean("NON_UNIQUE");

				if (!nonUnique && columnName != null && indexName != null) {
					uniqueIndices.put(columnName, indexName);
				}
			}

			while (columnRs.next()) {
				String columnName = columnRs.getString("COLUMN_NAME");

				int sqlType = columnRs.getInt("DATA_TYPE");
				String sqlTypeName = columnRs.getString("TYPE_NAME");
				String columnDefaultValue = columnRs.getString("COLUMN_DEF");

				String remarks = columnRs.getString("REMARKS");
				if (remarks == null && DatabaseUtil.isOracleDataBase(databaseMetaData)) {
					remarks = getOracleColumnComments(tableName, columnName);
				}

				boolean isNullable = (DatabaseMetaData.columnNullable == columnRs
						.getInt("NULLABLE"));
				int size = columnRs.getInt("COLUMN_SIZE");
				int decimalDigits = columnRs.getInt("DECIMAL_DIGITS");

				boolean isPk = primaryKeys.contains(columnName);
//				boolean isIndexed = indices.contains(columnName);
				String uniqueIndex = null;
				if(!isPk){
					uniqueIndex = (String) uniqueIndices.get(columnName);
				}
//				List columnsInUniqueIndex = null;
//				if (uniqueIndex != null) {
//					columnsInUniqueIndex = (List) uniqueColumns.get(uniqueIndex);
//				}
//
//				boolean isUnique = columnsInUniqueIndex != null && columnsInUniqueIndex.size() == 1;

				XColumn column = new XColumn(sqlType, sqlTypeName, columnName,
						size, decimalDigits, isPk, isNullable, uniqueIndex, columnDefaultValue, remarks);

				if(null != foreignKeys.get(columnName)){
					ForeignKey key = foreignKeys.get(columnName);
					column.setForeignKey(key.getForeignkey());
					column.setForeignTable(key.getForeigntable());
				}
				
				if (defaultColumn.indexOf("," + columnName + ",") != -1){
					column.setStatus(false);
				}
				columns.add(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return columns;
	}

	private ResultSet getColumnsResultSet(String tableName)
			throws SQLException {
		return databaseMetaData.getColumns(getCatalog(), getSchema(), tableName, null);
	}

	private List<String> getTablePrimaryKeys(String tableName)
			throws SQLException {
		List primaryKeys = new LinkedList();
		ResultSet primaryKeyRs = null;
		try {
			primaryKeyRs = databaseMetaData.getPrimaryKeys(getCatalog(), getSchema(), tableName);
			while (primaryKeyRs.next()) {
				String columnName = primaryKeyRs.getString("COLUMN_NAME");
				primaryKeys.add(columnName);
			}
		} finally {
			DatabaseUtil.close(primaryKeyRs);
		}
		return primaryKeys;
	}

	public Map<String, ForeignKey> getImportedKeys(String tableName, String catalog, String schema) {
		Map<String, ForeignKey> FKMap = new HashMap<String, ForeignKey>();
		ResultSet rs = null;
		try {
			rs = databaseMetaData.getImportedKeys(catalog, schema, tableName);
			while (rs.next()) {
				String pktable = rs.getString("PKTABLE_NAME");
				String pkcol = rs.getString("PKCOLUMN_NAME");
//				String fktable = rs.getString("FKTABLE_NAME");
				String fkcol = rs.getString("FKCOLUMN_NAME");

				ForeignKey fkey = new ForeignKey();
				fkey.setForeignkey(pkcol);
				fkey.setForeigntable(pktable);

				FKMap.put(fkcol, fkey);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return FKMap;
	}

	private String getOracleTableComments(String table) {
		String sql = "SELECT comments FROM user_tab_comments WHERE table_name='"
				+ table + "'";
		return DatabaseUtil.queryForString(connection, sql);
	}

	private String getOracleColumnComments(String table,
			String column) {
		String sql = "SELECT comments FROM user_col_comments WHERE table_name='"
				+ table + "' AND column_name = '" + column + "'";
		return DatabaseUtil.queryForString(connection, sql);
	}
	
	public class ForeignKey {
		private String foreignkey;//
		private String foreigntable;//

		public String getForeignkey() {
			return foreignkey;
		}

		public void setForeignkey(String foreignkey) {
			this.foreignkey = foreignkey;
		}

		public String getForeigntable() {
			return foreigntable;
		}

		public void setForeigntable(String foreigntable) {
			this.foreigntable = foreigntable;
		}
	}
}
