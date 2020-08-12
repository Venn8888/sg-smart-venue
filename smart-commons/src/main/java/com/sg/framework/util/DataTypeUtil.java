package com.sg.framework.util;


import java.sql.Types;
import java.util.HashMap;
/**
 * 
 */
public class DataTypeUtil {
	public static final String[] ARRAY = {"java.lang.String", "java.lang.Boolean", "java.lang.Long", "java.lang.Double", "java.math.BigDecimal", "java.util.Date", "java.sql.Clob"};

	private final static IntStringMap _preferredJavaTypeForSqlType = new IntStringMap();
	 
	public static boolean isFloatNumber(String javaType) {
		if(javaType.endsWith("Float") || javaType.endsWith("Double") || javaType.endsWith("BigDecimal") || javaType.endsWith("BigInteger")) {
			return true;
		}
		if(javaType.endsWith("float") || javaType.endsWith("double") || javaType.endsWith("BigDecimal") || javaType.endsWith("BigInteger")) {
            return true;
        }
		return false;
	}
	
	public static boolean isIntegerNumber(String javaType) {
		if(javaType.endsWith("Long") || javaType.endsWith("Integer") || javaType.endsWith("Short") || javaType.endsWith("Byte")) {
			return true;
		}
		if(javaType.endsWith("long") || javaType.endsWith("int") || javaType.endsWith("short") || javaType.endsWith("byte")) {
            return true;
        }
		return false;
	}

	public static boolean isDate(String javaType) {
		if(javaType.endsWith("Date") || javaType.endsWith("Time")) {
			return true;
		}
		return false;
	}

	public static boolean isDateTime(String javaType) {
		if(javaType.endsWith("Timestamp")) {
			return true;
		}
		return false;
	}
	
	public static boolean isString(String javaType) {
		if(javaType.endsWith("String")) {
			return true;
		}
		return false;
	}
	
	public static String getJavaType(int sqlType, int size,
			int decimalDigits) {
		if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC)
				&& decimalDigits == 0) {
			if (size == 1) {
				return "java.lang.Boolean";
			} else if (size < 3) {
				return "java.lang.Byte";
			} else if (size < 5) {
				return "java.lang.Short";
			} else if (size < 10) {
				return "java.lang.Integer";
			} else if (size < 19) {
				return "java.lang.Long";
			} else {
				//return "java.math.BigDecimal";
				return "java.lang.Long";
			}
		}
		String result = _preferredJavaTypeForSqlType.getString(sqlType);
		if (result == null) {
			result = "java.lang.Object";
		}
		return result;
	}
		   
   static {
      _preferredJavaTypeForSqlType.put(Types.TINYINT, "java.lang.Byte");
      _preferredJavaTypeForSqlType.put(Types.SMALLINT, "java.lang.Short");
      _preferredJavaTypeForSqlType.put(Types.INTEGER, "java.lang.Integer");
      _preferredJavaTypeForSqlType.put(Types.BIGINT, "java.lang.Long");
      _preferredJavaTypeForSqlType.put(Types.REAL, "java.lang.Float");
      _preferredJavaTypeForSqlType.put(Types.FLOAT, "java.lang.Double");
      _preferredJavaTypeForSqlType.put(Types.DOUBLE, "java.lang.Double");
      _preferredJavaTypeForSqlType.put(Types.DECIMAL, "java.lang.Double");
      _preferredJavaTypeForSqlType.put(Types.NUMERIC, "java.lang.Double");
      _preferredJavaTypeForSqlType.put(Types.BIT, "java.lang.Boolean");
      _preferredJavaTypeForSqlType.put(Types.BOOLEAN, "java.lang.Boolean");
      _preferredJavaTypeForSqlType.put(Types.CHAR, "java.lang.String");
      _preferredJavaTypeForSqlType.put(Types.VARCHAR, "java.lang.String");
      _preferredJavaTypeForSqlType.put(Types.LONGVARCHAR, "java.lang.String");
      _preferredJavaTypeForSqlType.put(Types.BINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.VARBINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.LONGVARBINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.DATE, "java.util.Date");
      _preferredJavaTypeForSqlType.put(Types.TIME, "java.util.Date");
      _preferredJavaTypeForSqlType.put(Types.TIMESTAMP, "java.util.Date");
      _preferredJavaTypeForSqlType.put(Types.CLOB, "java.lang.String");
      _preferredJavaTypeForSqlType.put(Types.BLOB, "java.sql.Blob");
      _preferredJavaTypeForSqlType.put(Types.ARRAY, "java.sql.Array");
      _preferredJavaTypeForSqlType.put(Types.REF, "java.sql.Ref");
      _preferredJavaTypeForSqlType.put(Types.STRUCT, "java.lang.Object");
      _preferredJavaTypeForSqlType.put(Types.JAVA_OBJECT, "java.lang.Object");
   }
		
   private static class IntStringMap extends HashMap {

		public String getString(int i) {
			return (String) get(new Integer(i));
		}

		public String[] getStrings(int i) {
			return (String[]) get(new Integer(i));
		}

		public void put(int i, String s) {
			put(new Integer(i), s);
		}

		public void put(int i, String[] sa) {
			put(new Integer(i), sa);
		}
	}
	   
}
