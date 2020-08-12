package com.sg.framework.util;

import java.io.IOException;
import java.util.MissingResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public final class PropertiesUtil {
	private static Environment environment;

	@Autowired
	protected void set(Environment environment) throws IOException {
		PropertiesUtil.environment = environment;
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return environment.getProperty(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key, String defaultValue) {
		try {
			String value = environment.getProperty(key);
			if (DataUtil.isEmpty(value)) {
				return defaultValue;
			}
			return value;
		} catch (MissingResourceException e) {
			return defaultValue;
		}
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(environment.getProperty(key));
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		String value = environment.getProperty(key);
		if (StringUtil.isBlank(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = environment.getProperty(key);
		if (StringUtil.isBlank(value)) {
			return defaultValue;
		}
		return new Boolean(value);
	}
}
