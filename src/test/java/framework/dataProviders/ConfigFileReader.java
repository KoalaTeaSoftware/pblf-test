package framework.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    // see https://www.toolsqa.com/selenium-cucumber-framework/read-configurations-from-property-file/

    private Properties properties;
    //private final String propertyFilePath= "configs//Configuration.properties";

    /**
     * Opens and reads the given properties file
     *
     * @param propertyFilePath - a path relative to the root folder (root of the code of the project)
     *                         - e.g. src/sut/resources/sut.properties
     */
    public ConfigFileReader(String propertyFilePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at :" + propertyFilePath + ":");
        }
    }

    public String getProperty(String propertyName) {
        String property = properties.getProperty(propertyName);
        if (property != null) return property;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }
//    public String getDriverPath() {
//        String driverPath = properties.getProperty("driverPath");
//        if (driverPath != null) return driverPath;
//        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
//    }
//
//    public long getImplicitlyWait() {
//        String implicitlyWait = properties.getProperty("implicitWait");
//        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
//        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
//    }
//
//    public String getSutUrl() {
//        String domainName = properties.getProperty("domainName");
//        String protocol = properties.getProperty("protocol");
//        String url = protocol + "://" + domainName;
//        if (url != null) return url;
//        else throw new RuntimeException("url not specified in the Configuration.properties file.");
//    }

}
