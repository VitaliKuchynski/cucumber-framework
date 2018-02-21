package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ConfigSaucelabs {
    //Initialises final variable and assigns user name
    public static final String USERNAME = "Vitali.Kuchynski";
    //Initialises final variable and assigns key provided bu sauselabs
    public static final String ACCESS_KEY = "c280c833-d1f5-4647-8e3c-de584563b081";
    //Initialises final variable and assigns URLSAUSLABS
    public static final String URLSAUSLABS = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    public  WebDriver setSaucelabsDriver() throws MalformedURLException {
        //Sets capabilities to remote sauselabs driver
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "31.0");
        caps.setCapability("screenResolution", "2560x1600");
        //Instants of remote driver
        WebDriver driver = new RemoteWebDriver(new URL(URLSAUSLABS),caps);
        return driver;
    }

}
