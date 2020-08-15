package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {

    private static Properties applicationProperties;

    private PropertiesConfig() {
    }

    /**
     * If method invoke first time - create new instance of Properties (applicationProperties)
     * Another time - load to applicationProperties file properties by path propertiesPathAboutResources
     *
     * @param propertiesPathAboutResources - path to properties after resources/ folder
     */
    public static void loadProperties(String propertiesPathAboutResources) {
        if (applicationProperties == null) {
            applicationProperties = new Properties();
        }
        InputStream propertiesStream = PropertiesConfig.class.getClassLoader().getResourceAsStream(propertiesPathAboutResources);
        try {
            applicationProperties.load(propertiesStream);
        } catch (IOException e) {
            System.out.println("Properties can't be loaded");
        }
    }

    public static String getProperty(String key) {
        return applicationProperties.getProperty(key);
    }


}
