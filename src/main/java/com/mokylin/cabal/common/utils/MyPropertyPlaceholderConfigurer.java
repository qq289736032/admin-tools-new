package com.mokylin.cabal.common.utils;

import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.context.support.ServletContextResource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/12 16:48
 * 项目: admin-tools
 */
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String, String> properties = new HashMap<String, String>();

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void setLocation(Resource location) {
        String path = ((ServletContextResource)location).getPath();

        location = resourceLoader.getResource(path);

        super.setLocation(location);
    }

    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        // cache the properties
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(
                DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);

        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            String stringKey = String.valueOf(entry.getKey());
            String stringValue = String.valueOf(entry.getValue());
            stringValue = helper.replacePlaceholders(stringValue, props);
            properties.put(stringKey, stringValue);
        }
        super.processProperties(beanFactoryToProcess, props);
    }

    public static Map<String, String> getProperties() {
        return properties;
    }

    public static String getProperty(String key) {
        return properties.get(key);
    }
}
