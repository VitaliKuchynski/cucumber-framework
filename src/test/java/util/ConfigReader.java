package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private String url;
    private String chromeDriverPath;
    private String firefoxDriverPath;
    private String browser;
    private String environment;

    public ConfigReader() {
        //Instance of properties
        Properties prop = new Properties();
        //Default instance of InputStream
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.url = prop.getProperty("url");
            this.chromeDriverPath = prop.getProperty("chrome_driver_path");
            this.firefoxDriverPath = prop.getProperty("firefox_driver_path");
            this.browser = prop.getProperty("browser");
            this.environment =prop.getProperty("environment");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //beginning of getters block
    public String getUrl() {
        return url;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getFirefoxDriverPath() {
        return firefoxDriverPath;
    }

    public String getBrowser() {
        return browser;
    }

    public String getEnvironment() {
        return environment;
    }//end of getters block

}
