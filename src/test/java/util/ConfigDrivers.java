package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class ConfigDrivers {
    //Initialise variable
    private String browser;
    //Initialise WebDriver default variable
    private WebDriver drivers = null;
    //Constructor gets browser name
    public ConfigDrivers(String browser) {
        this.browser = browser;
    }
    //Sets and returns web browser
    public WebDriver setBrowser(){
        ConfigReader configReader = new ConfigReader();

        //Switch statement goes through browsers name
        switch (browser)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.drivers", configReader.getChromeDriverPath());
                drivers = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.drivers", configReader.getFirefoxDriverPath());
                drivers = new FirefoxDriver();
                break;
            case "safari":
                drivers = new SafariDriver();
                break;
            default:
                drivers = new ChromeDriver();
                System.setProperty("webdriver.chrome.drivers", configReader.getChromeDriverPath());
                break;
        }
        return drivers;
    }

}
