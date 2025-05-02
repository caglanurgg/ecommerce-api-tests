package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(ConfigurationReader.class.getClassLoader().getResourceAsStream("config/configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("configuration.properties file not found. Check the path.");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}

