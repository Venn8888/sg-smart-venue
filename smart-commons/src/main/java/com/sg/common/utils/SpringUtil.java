package com.sg.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringUtil.context = context;
	}

	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}
	
	
	public static <T> T getBean(Class clazz) {
		return (T) context.getBean(clazz);
	}
}
