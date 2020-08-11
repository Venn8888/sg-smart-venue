package com.sg.common.utils;

import com.alibaba.fastjson.JSON;
import com.sg.common.exception.BusinessException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            BeanUtils.copyProperties(instance, map);
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



	public static void copy(Object target, Object source) {
		try {
			BeanUtils.copyProperties(source, target);
		}catch (Exception e) {
			logger.error("error happend when creating responsible chain:{}", e);
			throw new BusinessException("数据转换失败！");
		}
	}
}
