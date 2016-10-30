package com.cowlib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileLoader.class);


    private PropertyFileLoader() {
    }

    public static Properties getProperties(String propertiesFileName) {

        LOGGER.info("loading properties from class path : {}", propertiesFileName);

        ClassPathResource propertiesFile = new ClassPathResource(propertiesFileName);
        Properties properties = new Properties();
        if (propertiesFile.exists()) {
            try {
                properties = PropertiesLoaderUtils.loadProperties(propertiesFile);
            } catch (IOException e) {
                LOGGER.error("loading properties fail", e);
            }
        }
        return properties;
    }

}
