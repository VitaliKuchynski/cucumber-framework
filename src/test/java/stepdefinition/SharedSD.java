package stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import util.ConfigDrivers;
import util.ConfigReader;
import util.ConfigSaucelabs;

import java.net.MalformedURLException;

import static framework.BasePage.pageLoadingWait;

public class SharedSD {
    //Initialise web driver variable
	private static WebDriver driver = null;

	@Before
    public static void before() throws MalformedURLException {
	    //Instance of config reader
        ConfigReader configReader = new ConfigReader();
        //Instance of config driver
        ConfigDrivers configDrivers = new ConfigDrivers(configReader.getBrowser());
        String environment = configReader.getEnvironment();

        switch (environment)
        {
            case "local":
                driver = configDrivers.setBrowser();
                break;
            case "sauselabs":
                ConfigSaucelabs configSaucelabs = new ConfigSaucelabs();
                driver = configSaucelabs.setSaucelabsDriver();
                break;
            default:
                driver = configDrivers.setBrowser();
                break;
        }
        pageLoadingWait(10);
        driver.get(configReader.getUrl());
        driver.manage().window().maximize();
    }


    @After
	public static void after() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}
	public static WebDriver getDriver() {
		return driver;
	}
}
