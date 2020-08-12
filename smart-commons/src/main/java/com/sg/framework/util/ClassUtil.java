/* ================================================================
 *
 * 项目名称：framework-core 
 * 文件名称：ClassUtil.java
 * 创建时间：2009-3-19 下午02:44:25
 * 文件作者：wxy 
 *
 * ================================================================== 
 *    
 * Copyright © 2008 Zoomlion Co., Ltd. All rights reserved. 
 *
 * ================================================================*/
package com.sg.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sg.framework.annotation.SequenceColumn;
import com.sg.framework.annotation.UniqueIndexColumn;
import com.sg.framework.exception.BusinessException;

/**
 * Class及method相关方法的处理
 * <p>
 * ClassUtil.java
 * </p>
 * 
 * @author 
 * @version 1.0 2009-3-19
 */

public class ClassUtil {
	private static final Logger logger = LogManager.getLogger(ClassUtil.class);
	
	public static Class getFieldClass(Class clazz, String fieldName){
		try {
			Class clz = clazz.getDeclaredField(fieldName).getType();
			return clz;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Set<Field> getDeclaredFields(Class clazz){
		try {
//			return clazz.getDeclaredField(fieldName);
			Set<Field> fields = new HashSet<Field>();
			for (;clazz != Object.class; clazz = clazz.getSuperclass()) {  
	            try {  
	            	Field[] decFields = clazz.getDeclaredFields();
	            	for(Field field : decFields){
	            		boolean isStatic = Modifier.isStatic(field.getModifiers());
	                    if(!isStatic) {
	                    	fields.add(field);
	                    }
	            	}
	            } catch (Exception e) { 
	            	logger.error(e.getMessage());
	            }  
	        }  
			return fields;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Field getField(Class clazz, String fieldName){
		try {
//			return clazz.getDeclaredField(fieldName);
			Field field = null;
			for (;clazz != Object.class; clazz = clazz.getSuperclass()) {  
	            try {  
	                field = clazz.getDeclaredField(fieldName);  
	                break;  
	            } catch (NoSuchFieldException e) { 
	            	logger.error(e.getMessage());
	            }  
	        }  
			return field;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Object newInstance(Class clazz){
		try {
			Object object = Class.forName(clazz.getName()).newInstance();
			return object;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	
	public static Object newInstance(String clazz){
		try {
			Object object = Class.forName(clazz).newInstance();
			return object;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	/**
	 * 从实体中通过@Id注解取对象的主码fieldname
	 * 
	 * @param o
	 * @return
	 */
	public static String getIdFieldName(Object o) {
		return getIdFieldName(o.getClass());
	}

	/**
	 * 从实体中通过@Id注解取对象的主码fieldname
	 * 
	 * @param classObj
	 *            Class对象
	 * @return
	 */
	public static String getIdFieldName(Class classObj) {
		String idFieldName = null;
		Method[] methods = classObj.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")
					&& !method.getName().equals("getComponent")
					&& !method.getName().equals("getClass")) {
				if (method.getAnnotation(Id.class) != null) {
					Column anColumn = (Column) method.getAnnotation(Column.class);
					idFieldName = anColumn.name();
				}
			}
		}
		if (idFieldName == null) {
			idFieldName = "ID";
		}
		return idFieldName;
	}
	
	
	/**
	 * 从实体中通过@Id注解取对象的主码fieldname
	 * 
	 * @param o
	 * @return
	 */
	public static String getIdPropertyName(Object o) {
		return getIdPropertyName(o.getClass());
	}

	/**
	 * 从实体中通过@Id注解取对象的主码fieldname
	 * 
	 * @param classObj
	 *            Class对象
	 * @return
	 */
	public static String getIdPropertyName(Class classObj) {
		String idPropertyName = null;
		Method[] methods = classObj.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")
					&& !method.getName().equals("getComponent")
					&& !method.getName().equals("getClass")) {
				if (method.getAnnotation(Id.class) != null) {
					idPropertyName = getPropertyName(method);
				}
			}
		}
		if (idPropertyName == null) {
			idPropertyName = "id";
		}
		return idPropertyName;
	}
	
	/**
	 * 取方法对应的属性名
	 * @param method
	 * @return
	 */
	public static String getPropertyName(Method method) {
		return method.getName().substring(3, 4).toLowerCase()
				+ method.getName().substring(4);
	}
	
	/**
	 * 取方法对应的字段名
	 * @param method
	 * @return
	 */
	public static String getFieldName(Method method) {
		String s= method.getName().substring(3, 4).toLowerCase()
				+ method.getName().substring(4);
		StringBuilder idFieldName = new StringBuilder();
		for(int i = 0; i < s.length(); i++){
			if(String.valueOf(s.charAt(i)).equals(String.valueOf(s.charAt(i)).toUpperCase())){
				idFieldName.append("_");
			}
			idFieldName.append(String.valueOf(s.charAt(i)));
		}
		return StringUtil.upperCase(idFieldName.toString());
	}

	/**
	 * 从实体中通过@Id注解取对象的主码get方法
	 * 
	 * @param classObj
	 * @return
	 */
	public static Method getIdMethod(Class classObj) {
		Method r = null;
		Method[] methods = classObj.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(Id.class) != null) {
				r = method;
			}
		}
		return r;
	}
	
	public static String getClassSimpleName(Class classObj){
		if(classObj==null){
			return "";
		}
		return classObj.getSimpleName();
		
	}
	
	public static final char UNDERLINE = '_';
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		
		if(param.indexOf(UNDERLINE) > -1){
			param = StringUtil.lowerCase(param);
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel2(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		if(param.indexOf(UNDERLINE) > -1){
			param = StringUtil.lowerCase(param);
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			// String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position - 1, position + 1,
					sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	
	/** 
     * 利用反射获取指定对象的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标属性的值 
     */  
    public static Object getFieldValue(Object obj, String fieldName) {  
        Object result = null;  
        Field field = ClassUtil.getField(obj, fieldName);  
        if (field != null) {  
            field.setAccessible(true);  
            try {  
                result = field.get(obj);  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  

    /** 
     * 利用反射获取指定对象里面的指定属性 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @return 目标字段 
     */  
    private static Field getField(Object obj, String fieldName) {  
        return getField(obj.getClass(), fieldName); 
    }  

    /** 
     * 利用反射设置指定对象的指定属性为指定的值 
     * @param obj 目标对象 
     * @param fieldName 目标属性 
     * @param fieldValue 目标值 
     */  
    public static void setFieldValue(Object obj, String fieldName,Object fieldValue) {  
        Field field = ClassUtil.getField(obj, fieldName);  
        if (field != null) {  
            try {  
                field.setAccessible(true);  
                field.set(obj, fieldValue);  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace(); 
                logger.error(e.getMessage());
            } catch (IllegalAccessException e) {  
                e.printStackTrace(); 
                logger.error(e.getMessage());
            }  
        }  
    }  

    public static List<String> getMany2OneFields(Class clazz) {
		List<String> list = new ArrayList<String>();
		Method[] methods =  clazz.getMethods();
		for (Method method : methods) {
			String fieldName = null;
			if (method.getAnnotation(ManyToOne.class) != null) {
				fieldName = method.getName();
			}
			if (fieldName != null) {
				fieldName = fieldName.substring(3);
				fieldName = StringUtil.lowerCaseFirstCharacter(fieldName);
				
				list.add(fieldName);
			}
		}
		return list;
	}
	
	
	public static Map<String, SequenceColumn> getSequenceFields(Class clazz) {
		Map<String, SequenceColumn> map = new HashMap<String, SequenceColumn>();
		Method[] methods =  clazz.getMethods();
		for (Method method : methods) {
			String fieldName = null;
			SequenceColumn annotation = method.getAnnotation(SequenceColumn.class);
			if (null != annotation) {
				fieldName = method.getName();
				fieldName = fieldName.substring(3);
				fieldName = StringUtil.lowerCaseFirstCharacter(fieldName);
				
				map.put(fieldName, annotation);
			}
		}
		return map;
	}
	
	
	public static Map<String, UniqueIndexColumn> getUniqueIndexFields(Class clazz) {
		Map<String, UniqueIndexColumn> map = new HashMap<String, UniqueIndexColumn>();
		Method[] methods =  clazz.getMethods();
		for (Method method : methods) {
			String fieldName = null;
			UniqueIndexColumn annotation = method.getAnnotation(UniqueIndexColumn.class);
			if (null != annotation) {
				fieldName = method.getName();
				fieldName = fieldName.substring(3);
				fieldName = StringUtil.lowerCaseFirstCharacter(fieldName);
				map.put(fieldName, annotation);
			}
		}
		return map;
	}
	
	public static Class getClassByName(String className){
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
		return clazz;
	}
	
	public static final Method getSetMethod(String fieldName, Class<?> clazz) {
		try {
			Class<?>[] parameterTypes = new Class[1];
			Field field = clazz.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			Method method = clazz.getMethod(sb.toString(), parameterTypes);
			return method;
		} catch (Exception e) {
			logger.error(e.getMessage());
//			throw new BusinessException(e.getMessage());
		}
		return null;
	}

	public static final Method getGetMethod(String fieldName, Class<?> clazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		try {
			return clazz.getMethod(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
//			throw new BusinessException(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 取全部Set方法
	 *
	 * @param T
	 * @return
	 */
	public static final Set<Method> getSetMethods(Class<?> T) {
		Method[] methods = T.getMethods();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				methodSet.add(method);
			}
		}
		return methodSet;
	}
	
	/**
	 * 取全部Set方法
	 *
	 * @param T
	 * @return
	 */
	public static final Set<Method> getGetMethods(Class<?> T) {
		Method[] methods = T.getMethods();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				methodSet.add(method);
			}
		}
		return methodSet;
	}

	/**
	 * 取自定义Set方法
	 *
	 * @param T
	 * @return
	 */
	public static final Set<Method> getDeclaredSetMethods(Class<?> T) {
		Method[] methods = T.getDeclaredMethods();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				methodSet.add(method);
			}
		}
		return methodSet;
	}

	/**
	 * 取自定义get方法
	 * 
	 * @param T
	 * @return
	 */
	public static final Set<Method> getDeclaredGetMethods(Class<?> T) {
		Method[] methods = T.getDeclaredMethods();
		Set<Method> methodSet = new HashSet<Method>();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				methodSet.add(method);
			}
		}
		return methodSet;
	}
	
//	public static void main(String[] args){
//		//System.out.println(DataTypesUtil.isSimpleClass(FileUtil.class));
////		String s = "12345678";
////		for(int i = 0; i < s.length(); i++){
////			System.out.println(s.charAt(i));
////		}
////		
//		
//		String s= "getMethodId".substring(3);
//		StringBuilder idFieldName = new StringBuilder();
//		for(int i = 0; i < s.length(); i++){
//			if(String.valueOf(s.charAt(i)).equals(String.valueOf(s.charAt(i)).toUpperCase())){
//				idFieldName.append("_");
//			}
//			idFieldName.append(String.valueOf(s.charAt(i)));
//		}
//		System.out.println(StringUtil.upperCase(idFieldName.toString()));
//		System.out.println(ClassUtil.camelToUnderline("o.userName"));
//		System.out.println(ClassUtil.underlineToCamel("o.userName"));
//		System.out.println(ClassUtil.underlineToCamel2("USER_NAME"));
//		
//		//转换为驼峰
//		String s = "appInfo.userName";
//		if(s.indexOf(".") > -1){
//			String[] ss = s.split("\\.");
//			String s1 = StringUtil.upperCase(ClassUtil.camelToUnderline(ss[1]));
//			System.out.println(ss[0] + "." + s1);
//		}else{
//			System.out.println(StringUtil.upperCase(ClassUtil.camelToUnderline(s)));
//		}
//	}
}
