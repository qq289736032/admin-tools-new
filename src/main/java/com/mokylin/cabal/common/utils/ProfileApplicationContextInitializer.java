package com.mokylin.cabal.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/12 17:28
 * 项目: admin-tools
 */
public class ProfileApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger _logger = LoggerFactory.getLogger(ProfileApplicationContextInitializer.class);

    public static String profile;
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            ClassPathResource resource = new ClassPathResource("properties/profile.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);

            profile = properties.getProperty("profiles.active");

            applicationContext.getEnvironment().setActiveProfiles(profile.split(","));
            _logger.info("Active spring profile: {}", profile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}