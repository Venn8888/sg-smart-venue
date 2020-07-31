package com.sg.common.utils;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/6/13
 */
@Component
public class ConfigFromNacosUtil {

    private final ConfigurableApplicationContext configurableApplicationContext;

    public ConfigFromNacosUtil(ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;
    }

    public  String getProfileByKey(String key){
        return configurableApplicationContext.getEnvironment().getProperty(key);
    }

}
