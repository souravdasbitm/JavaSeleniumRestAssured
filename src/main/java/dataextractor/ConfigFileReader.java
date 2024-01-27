package dataextractor;

import utils.PropertyUtils;

import java.io.FileNotFoundException;
import java.util.Properties;

/*
Singleton Config File Reader
*/
@SuppressWarnings("java:S6548")
public class ConfigFileReader {
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/configs/";


    //Environment

    private static final String CONFIG_PROPERTIES = "_config.properties";

    private static final String STG_CONFIG_PROPERTIES = "stg" + CONFIG_PROPERTIES;
    private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
    private static final String QA_CONFIG_PROPERTIES = "qa" + CONFIG_PROPERTIES;
    private static final String INT_CONFIG_PROPERTIES = "int" + CONFIG_PROPERTIES;
    private static ConfigFileReader configFileReader;
    private final Properties properties;

    /*
     * This will check for the env value from Jenkins/Maven first. If it does not
     * get any input from Jenkins/mvn cmd line, then, will take
     * maven command mvn clean install -Denv=staging
     * IF running from local without mvn command the if logic is placed
     * default is staging
     */
    String env = (System.getProperty("env") != null)
            ? System.getProperty("env")
            : "staging";
    private ConfigFileReader() throws FileNotFoundException {
        switch (env.toLowerCase()) {
            case ("staging"):
                properties = getConfigPropertyFile(STG_CONFIG_PROPERTIES);
                break;
            case ("qa"):
                properties = getConfigPropertyFile(QA_CONFIG_PROPERTIES);
                break;
            case ("dev"):
                properties = getConfigPropertyFile(INT_CONFIG_PROPERTIES);
                break;
            case ("preprod"):
                properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
                break;
            default:
                throw new IllegalArgumentException("Invalid Env : " + System.getProperty("env").toLowerCase());
        }
    }

    private Properties getConfigPropertyFile(String configFile) throws FileNotFoundException {
        return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
    }

    private String getPropertyValue(String propertyValue) {
        String prop = properties.getProperty(propertyValue);
        if (prop != null) {
            return prop.trim();
        } else {
            throw new NullPointerException("The " + propertyValue + " is not defined in Config.properties");
        }
    }

    public static ConfigFileReader getInstance() throws FileNotFoundException {
        if (configFileReader == null) {
            configFileReader = new ConfigFileReader();
        }
        return configFileReader;
    }

    public String getUiUrl() {
        return getPropertyValue("UI_URL");
    }
    public String getApiUrl() {
        return getPropertyValue("API_URL");
    }
    public String getBrowserProperty(){
        return getPropertyValue("headless");
    }


}


