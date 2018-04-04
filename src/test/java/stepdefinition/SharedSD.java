package stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
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

        switch(environment)
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
        driver.get(configReader.getUrl());
        driver.manage().window().maximize();
    }


//    @After
//	public static void after() {
//
//		if (driver != null) {
//			driver.manage().deleteAllCookies();
//			driver.quit();
//		}
//	}
    //Takes screenshot if scenario fails and adds it to cucumber report
//@After
//    public void actionsAfterScenario(Scenario scenario){
//	    //Verifies if the scenario fails and make a screenshot
//        if (scenario.isFailed()) {
//            //Takes screenshot to cucumber report
//            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
//            //Takes screenshot to allure report
//            attachFailed(driver);
//
//        }
//
//    driver.manage().deleteAllCookies();
//    driver.quit();
//    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @After
    public void actionsAfterScenario(Scenario scenario){
	    //Verifies if the scenario fails and make a screenshot
        if (scenario.isFailed()) {
            //Takes screenshot to cucumber report
            saveScreenshot(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            //Takes screenshot to cucumber report
            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
    driver.quit();
	}

    //Getters for driver
	public static WebDriver getDriver() {
		return driver;
	}
}
