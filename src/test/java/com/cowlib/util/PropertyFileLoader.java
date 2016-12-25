package com.cowlib.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileLoader {

    private static final Logger logger = LoggerFactory.getLogger(PropertyFileLoader.class);

    private PropertyFileLoader() {
    }

    public static Properties getProperties(String propertiesFileName) {

        logger.info("loading properties from class path : {}", propertiesFileName);

        ClassPathResource propertiesFile = new ClassPathResource(propertiesFileName);
        Properties properties = new Properties();
        if (propertiesFile.exists()) {
            try {
                properties = PropertiesLoaderUtils.loadProperties(propertiesFile);
            } catch (IOException e) {
                logger.error("loading properties fail", e);
            }
        }
        return properties;
    }
    public static String getResourceAsString(String resourcePath) throws IOException {
        Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:" + resourcePath);
        InputStream inputStream = resource.getInputStream();
        return IOUtils.toString(inputStream, "UTF-8");
    }

}
