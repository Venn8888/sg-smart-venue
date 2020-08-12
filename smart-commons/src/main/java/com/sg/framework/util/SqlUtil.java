package com.sg.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;


public class SqlUtil {
	/**
	 * 去除select 子句，未考虑union的情况
	 * @param sql
	 * @return
	 */
    public static String removeSelect(String sql) {
    	if(StringUtil.isEmpty(sql)){
    		return null;
    	}
    	sql = sql.replaceAll("\t", "").replaceAll("\n", "");
        int beginPos = sql.lastIndexOf("from ") > -1 ? sql.lastIndexOf("from ") : sql.lastIndexOf("FROM ");
        Assert.isTrue(beginPos != -1, " hql : " + sql + " must has a keyword 'from'");
        return sql.substring(beginPos);
    }

	/**
	 * 去除select 子句，未考虑union的情况
	 * @param sql
	 * @return
	 */
    public static String getSelect(String sql) {
    	if(StringUtil.isEmpty(sql)){
    		return null;
    	}
    	sql = sql.replaceAll("\t", "").replaceAll("\n", "");
        int beginPos = sql.lastIndexOf("from ");
        Assert.isTrue(beginPos != -1, " hql : " + sql + " must has a keyword 'from'");
        return sql.substring(0, beginPos);
    }
    
    /**
     * 去除orderby 子句
     * @param sql
     * @return
     */
    public static String removeOrders(String sql) {
    	sql = sql.replaceAll("\t", "").replaceAll("\n", "");
    	if(StringUtil.isEmpty(sql)){
    		return null;
    	}
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    
    public static String removeFetchKeyword(String sql) {
    	return sql.replaceAll("(?i)fetch", "");
    }
	
	public static boolean isFilterPropertyExist(Object param) {
		if (param == null || param.equals("")
				|| param.toString().length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isWhereExist(String sql) {
		sql = sql.replaceAll("\t", "").replaceAll("\n", "");
		Pattern pattern = Pattern.compile(" where ", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(SqlUtil.removeSelect(sql));
		return matcher.find();
	}
	
	public static String[] parseAlias(String sql){
		sql = sql.replaceAll("\t", "").replaceAll("\n", "");
        int from = sql.indexOf("select") + 6;  
        int to = sql.indexOf("from");  
        String sqlAlias = sql.substring(from, to);  
  
        String[] columns = StringUtils.split(sqlAlias, ",");  
        String[] columnAlias = new String[columns.length];  
        int i = 0;  
        for (String alias : columns) {  
            String[] temp = StringUtils.split(alias, " ");  
            String t = temp[temp.length - 1].trim();  
            t = t.replaceAll("\"", "") ;
            columnAlias[i] = t;  
            i++;  
        }  
        return columnAlias;  
	}
	
    /**
     * 根据实体获取表名
     * @param entityClass
     * @return
     */
    public static String tableName(Class entityClass) {
		Table annotation = (Table) entityClass.getAnnotation(Table.class);
		
		if (null != annotation) {
			return annotation.name();
		}
		return entityClass.getSimpleName();
	}
//	public static void main(String[] args){
//		String[] ss = SqlUtil.parseAlias("select o.data_Log_Id as dataLogId,o.type as type,o.do_Type as actionType from Account o left join fetch o.org");
//		for(String s : ss){
//			System.out.println(s);
//		}
//	}
}
