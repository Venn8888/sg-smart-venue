package com.sg.framework.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * List集合相关处理类
 * 
 * @author wxy
 * @version 1.0 2010-3-30
 */
public class ListUtil {
	private static Logger log = Logger.getLogger(ListUtil.class);
	/**
	 * List是否为空的验证
	 * 
	 * @param parm
	 * @return
	 */
	public static boolean isNullOrEmpty(List parm) {
		boolean rtn = false;
		if (null == parm || parm.size() == 0) {
			rtn = true;
		}
		return rtn;
	}

	/**
	 * 查询o在dataList中的位置.从0开始
	 * 
	 * @param dataList
	 *            数据列表的list
	 * @param o
	 *            目标对象
	 * @param idFieldName
	 *            主码的字段名称 如果为空,则直接使用对象引用进行比较
	 * @return int >=0：位置 -1:未找到
	 */
	public static int getPosFromList(List<?> dataList, Object o, String idFieldName) {
		int r = -1;
		if (dataList == null || dataList.isEmpty() || o == null) {
			return r;
		}
		if (idFieldName == null) {// 如果没有指定主码,则自动比较两个对象的引用
			for (int i = 0; i < dataList.size(); i++) {
				if (dataList.get(i) == o) {
					r = i;
					break;
				}
			}
		} else {
			try {
				Object key = BeanUtil.getProperty(o, idFieldName);
				Object data = null;
				for (int i = 0; i < dataList.size(); i++) {
					data = dataList.get(i);
					if (data != null) {
						if (key.equals(BeanUtil.getProperty(data, idFieldName))) {
							r = i;
						}
					}
				}

			} catch (Exception e) {
				// 在方法名称不对的时候,系统在后台进行提示
				log.error("May idFieldName is wrong! idFieldName:"
						+ idFieldName + ",className:" + o.getClass().getName());
				e.printStackTrace();
			}
		}
		return r;
	}

	/**
	 * 查询o在dataList中的位置.从0开始
	 * 
	 * @param dataList
	 *            数据列表的list
	 * @param o
	 *            idValue 目标对象的主键
	 * @param idFieldName
	 *            主码的字段名称 如果为空,则直接使用对象引用进行比较
	 * @return int >=0：位置 -1:未找到
	 */
	public static int getPosFromListIdValue(List dataList, Object idValue,
			String idFieldName) {
		int r = -1;
		if (dataList == null || dataList.isEmpty() || idValue == null) {
			return r;
		}
		try {
			Method idMethod = dataList.get(0).getClass().getMethod(
					"get" + StringUtil.setFirstCharUpcase(idFieldName));
			Object value = null;
			int pos = 0;
			for (Object obj : dataList) {
				value = idMethod.invoke(obj, null);
				if (value.toString().equals(idValue.toString())) {
					r = pos;
					break;
				}
				pos++;
			}
		} catch (Exception e) {
			// 在方法名称不对的时候,系统在后台进行提示
			log.error("May idFieldName is wrong! idFieldName:" + idFieldName);
		}
		return r;
	}

	/**
	 * 排除list中重复的记录
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> uniqueList(List<T> list) {
		if (isNullOrEmpty(list)) {
			return list;
		}
		Map<T,String> map = new HashMap<T,String>(list.size());
		for (T o : list) {
			map.put(o, "1");
		}
		List<T> r = list;
		if (map.size() != list.size()) {
			r = new ArrayList<T>(map.size());
			for (T o : list) {
				if (map.get(o) != null) {
					r.add(o);
					map.remove(o);
				}
			}
		}
		map = null;
		list = null;
		return r;

	}

	/**
	 * 排除list中重复的记录
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> uniqueList(List<T> list, String idFieldName) {
		if (isNullOrEmpty(list)) {
			return list;
		}
		if (StringUtil.isEmpty(idFieldName)) {
			return uniqueList(list);
		}
		List<T> r = list;
		try {
			Object value = null;
			Map<Object,String> map = new HashMap<Object,String>(list.size());
			for (T o : list) {
				map.put(BeanUtil.getProperty(o, idFieldName), "1");
			}
			if (map.size() != list.size()) {
				r = new ArrayList<T>(map.size());
				for (T o : list) {
					value = BeanUtil.getProperty(o, idFieldName);
					if (map.get(value) != null) {
						r.add(o);
						map.remove(value);
					}
				}
			}
			map = null;
			list = null;
		} catch (Exception e) {
			// 在方法名称不对的时候,系统在后台进行提示
			log.error("May idFieldName is wrong! idFieldName:" + idFieldName);
			e.printStackTrace();
		}
		return r;

	}
	
	/**
	 * 对数组进行排重
	 * 
	 * @param t
	 * @return
	 */
	public static Serializable[] uniqueArray(Serializable[] t) {
		if (t == null || t.length < 1)
			return t;
		Serializable[] r = null;
		HashMap map = new HashMap(t.length);
		for (int i = 0; i < t.length; i++) {
			map.put(t[i], t[i]);
		}
		if (map.size() == t.length) {
			r = t;
		} else {
			r = (Serializable[]) map.values().toArray(
					new Serializable[map.size()]);
		}
		return r;
	}

	public static List getOneFieldValue(List list, String fieldName) {
		if (isNullOrEmpty(list)) {
			return new ArrayList();
		}
		if (StringUtil.isEmpty(fieldName)) {
			return list;
		}
		List r = new ArrayList(list.size());
		try {
			for (Object o : list) {
				r.add(BeanUtil.getProperty(o, fieldName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 将list按照指定长度分割为多个list
	 * 
	 * @param <T>
	 * @param list
	 * @param subListSize
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int subListSize) {
		List<List<T>> r = new ArrayList<List<T>>();
		if (isNullOrEmpty(list) || subListSize < 0) {
			r.add(list);
			return r;
		}
		int maxSize = subListSize;
		int subNum = list.size() / maxSize + 1;
		int endIndex = 0;
		for (int i = 0; i < subNum; i++) {
			if (i * maxSize < list.size()) {
				List<T> subList = new ArrayList<T>(maxSize);
				endIndex = (i + 1) * maxSize;
				endIndex = endIndex < list.size() ? endIndex : (list.size());
				subList.addAll(list.subList(i * maxSize, endIndex));
				r.add(subList);
			}
		}

		return r;
	}

	public static List<Long> toList(String[] strings) {
		if (strings == null || strings.length == 0) {
			return Collections.emptyList();
		}

		List<Long> list = new ArrayList<Long>(strings.length);
		for (String string : strings) {
			list.add(Long.valueOf(string));
		}

		return list;
	}

	/**
	 * 将List中数据放到map中
	 * 
	 * @param <T>
	 * @param list
	 * @param idFieldName
	 *            list中数据的主键的属性名称
	 * @return
	 */
	public static <T> Map<Object, T> toMap(List<T> list, String idFieldName) {
		if (isNullOrEmpty(list)) {
			return new HashMap<Object, T>();
		}
		Map<Object, T> r = new HashMap<Object, T>((list.size() + 1) * 2);
		try {
			for (T o : list) {
				r.put(BeanUtil.getProperty(o, idFieldName), o);
			}
		} catch (Exception e) {
			log.error("toMap error:", e);
		}
		return r;
	}

	/**
	 * 将字符列表转换成字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {
		if (isNullOrEmpty(list)) {
			return "";
		}
		String returnstr = "";
		for (String s : list) {
			returnstr += s;
		}
		return returnstr;
	}

	/**
	 * 将字符列表转换成字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String join(List list, String split) {
		if (isNullOrEmpty(list)) {
			return "";
		}
		if(null == split){
			split = "";
		}
		
		String str = "";
		for (Object s : list) {
			str += s + split;
		}
		if(str.length() >= split.length()){
			str = str.substring(0, str.length() - split.length());
		}
		return str;
	}
	/**
	 * 将一串类型相同的值或数组转换成列表
	 * 
	 * @param <T>
	 * @param values
	 * @return
	 */
	public static <T> List<T> covertToList(T... values) {
		List<T> list = Collections.emptyList();
		if (values != null) {
			list = new ArrayList<T>();
			for (T value : values) {
				list.add(value);
			}
		}
		return list;
	}
	
	
//	public static void main(String[] args){
//		List<Long> list = new ArrayList<Long>();
//		
//		list.add(-1l);
//		list.add(0l);
//		System.out.println(ListUtil.join(list, ","));
//	}
}
