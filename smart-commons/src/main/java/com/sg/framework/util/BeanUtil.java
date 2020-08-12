package com.sg.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.sg.framework.exception.BusinessException;

public class BeanUtil {
	private static final Log logger = LogFactory.getLog(BeanUtil.class);
	

    /**
     * 方法说明：将bean转化为另一种bean实体
     * 
     * @param object
     * @param entityClass
     * @return
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) {
        if(null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }


    /**
     * 方法说明：对象转换
     * 
     * @param source	原对象
     * @param target	目标对象
     * @param ignoreProperties	排除要copy的属性
     * @return
     */
    public static <T> T copy(Object source, Class<T> target, String...ignoreProperties){
        T targetInstance = null;
        try {
            targetInstance = target.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ArrayUtils.isEmpty(ignoreProperties)) {
            BeanUtils.copyProperties(source, targetInstance);
        }else {
            BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
        }
        return targetInstance;

    }

    /**
     * 方法说明：对象转换(List)
     * 
     * @param list	原对象
     * @param target	目标对象
     * @param ignoreProperties	排除要copy的属性
     * @return
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target, String...ignoreProperties){
        List<T> targetList = new ArrayList<T>();
        if(CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for(E e : list) {
            targetList.add(copy(e, target, ignoreProperties));
        }
        return targetList;
    }

    /**
     * 方法说明：map转化为对象
     * 
     * @param map
     * @param t
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t){
        try {
            T instance = t.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(instance, map);
            return instance;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 方法说明：对象转化为Map
     * 
     * @param object
     * @return
     */
    public static Map<?, ?> objectToMap(Object object){
        return convertBean(object, Map.class);
    }

    public static Object map2Bean(Class clazz, Map data) {
		try {
			for (Object key : data.keySet()) {
				if (data.get(key) instanceof Clob) {
					Clob clob = (Clob) data.get(key);
					try {
						data.put(key, clob.getSubString((long) 1, (int) clob.length()));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			Object bean = clazz.newInstance();
//			BeanUtils.populate(bean, map);
			for(Object key : data.keySet()){
				if(data.get(key) instanceof Map){
					Map nestData = (Map) data.get(key);
					for (Object nestKey : nestData.keySet()) {
						if (nestData.get(nestKey) instanceof Clob) {
							Clob clob = (Clob) nestData.get(nestKey);
							try {
								nestData.put(nestKey, clob.getSubString((long) 1, (int) clob.length()));
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
					Class nestClass = ClassUtil.getFieldClass(clazz, (String)key);
					Object nest = nestClass.newInstance();
					BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(nest);
					wrapper.setAutoGrowNestedPaths(true);
					wrapper.setPropertyValues(nestData);
					
					data.put(key, nest);
				}
			}
			
			BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);
			wrapper.setAutoGrowNestedPaths(true);
			wrapper.setPropertyValues(data);
			
			return bean;
		} catch (Exception e) {
			logger.error("error happend when creating responsible chain:{}", e);
			throw new BusinessException("数据转换失败！");
		}
	}
	
	public static List maps2Beans(Class clazz, List<Map> maps) {
		List list = new ArrayList();
		for(Map map : maps){
			list.add(map2Bean(clazz, map));
		}
		return list;
	}
	
	public static void copy(Object target, Object source) {
		try {
			BeanUtils.copyProperties(source, target);
		}catch (Exception e) {
			logger.error("error happend when creating responsible chain:{}", e);
			throw new BusinessException("数据转换失败！");
		}
	}

	public static void notNullCopy(Object target, Object source)
			throws BusinessException {
		try {
			BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			if (target == null)
				throw new BusinessException("No destination bean specified");
			if (source == null)
				throw new BusinessException("No origin bean specified");
			if (logger.isDebugEnabled())
				logger.debug("BeanUtils.copyProperties(" + target + ", " + source + ")");
			String name;
			Object object = null;
			if ((source instanceof DynaBean)) {
				DynaProperty[] ss = ((DynaBean)source).getDynaClass().getDynaProperties();
				for (int i = 0; i < ss.length; i++) {
					name = ss[i].getName();
					if ((beanUtilsBean.getPropertyUtils().isReadable(source, name))
							&& (beanUtilsBean.getPropertyUtils().isWriteable(target, name))) {
						object = ((DynaBean) source).get(name);
						beanUtilsBean.copyProperty(target, name, object);
					}
				}
			} else if ((source instanceof Map)) {
				Iterator it = ((Map) source).entrySet().iterator();
				while (((Iterator) it).hasNext()) {
					Map.Entry localEntry = (Map.Entry) ((Iterator) it).next();
					name = (String) localEntry.getKey();
					if (beanUtilsBean.getPropertyUtils().isWriteable(target, name))
						beanUtilsBean.copyProperty(target, name, localEntry.getValue());
				}
			} else {
				PropertyDescriptor[] properties = beanUtilsBean.getPropertyUtils().getPropertyDescriptors(source);
				for (int j = 0; j < properties.length; j++) {
					name = properties[j].getName();
					if ((!"class".equals(name))
						&& (beanUtilsBean.getPropertyUtils().isReadable(source, name))
						&& (beanUtilsBean.getPropertyUtils().isWriteable(target, name))) {
						object = beanUtilsBean.getPropertyUtils().getSimpleProperty(source, name);
						if (object != null && !StringUtil.isEmpty(String.valueOf(object))) {
							beanUtilsBean.copyProperty(target, name, object);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("error happend when creating responsible chain:{}", e);
			throw new BusinessException(e.getMessage());
		}
	}


	public static void setProperty(Object target, String field, Object value) {
		try {
			PropertyUtils.setProperty(target, field, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Object getProperty(Object target, String field) {
		try {
			return PropertyUtils.getProperty(target, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
