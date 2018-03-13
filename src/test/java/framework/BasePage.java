package framework;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinition.SharedSD;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class BasePage {
    //Gets title of page
    public String getTitle(){
        return SharedSD.getDriver().getTitle();
    }


    //Clicks on element
    public void clickOn(By locator) {
        findAndWaitOfWebElement(locator).click();
    }

    //Finds element and enters text
    public void sendText(By locator, String text) {
        findAndWaitOfWebElement(locator).sendKeys(text);
    }

    //Gets element ant returns string value
    public String getTextFromElement(By locator) {
        return findAndWaitOfWebElement(locator).getText();
    }

    //Waits, Gets radio-button and check it
    public void checkRadioButton(By locator) {
        findAndWaitOfWebElement(locator).click();
    }

    //Checks if element is selected
    public boolean isElementSelected(By locator) {
        boolean isSelectedResult = findAndWaitOfWebElement(locator).isSelected();
        return isSelectedResult;

    }

    //Checks if element is displayed
    public boolean isElementDisplayed(By locator) {
        boolean isDisplayedResult = findAndWaitOfWebElement(locator).isDisplayed();
        return isDisplayedResult;
    }

    //Checks if element is enabled
    public boolean isElementEnabled(By locator) {
        boolean isEnabledResult = findAndWaitOfWebElement(locator).isEnabled();
        return  isEnabledResult;
    }

//    //Selects value from drop-down list
//    public void selectDropdownListValue(Select dropDownValue, String value) {
//        try {
//            dropDownValue.selectByVisibleText(value);
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Screen shot should be taken");
//
//        }
//    }
//
//    //Selects value from drop-down list by index
//    public void selectDropdownListValue(Select dropDownValue, int index) {
//        try {
//            dropDownValue.selectByIndex(index);
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Screen shot should be taken");
//
//        }
//    }

    //Selects current dated from list of days
    public void selectCurrentDate(List<WebElement> element)  {
        //Create formatter to date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        //Instance of date, gets current date
        Date date = new Date();
        //Converts date  format to the string format
        String currentDate = simpleDateFormat.format(date);
        //Enhanced loop goes through list of web elements and selects current date
        for (WebElement day : element) {
            String expectedDay = day.getText();
            if (expectedDay.equals(currentDate)) {
                day.click();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    //Switches to windows
    public void switchToWindow(int index) {
        List<String> listOfWindows = new ArrayList<>(SharedSD.getDriver().getWindowHandles());
        SharedSD.getDriver().switchTo().window(listOfWindows.get(index));
    }

    //Switches to main window
    public void switchToRootWindow() {
        List<String> listOfWindows = new ArrayList<>(SharedSD.getDriver().getWindowHandles());
        for (int i = 1; i < listOfWindows.size(); i++) {
            SharedSD.getDriver().switchTo().window(listOfWindows.get(i));
            SharedSD.getDriver().close();
        }
        SharedSD.getDriver().switchTo().window(listOfWindows.get(0));
    }

    //Accepts the alert
    public void acceptAlert() {
        try {
            SharedSD.getDriver().switchTo().alert().accept();
            Thread.sleep(3000);
            System.out.println("Alert accepted.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is no alert");

        }
    }

    //Dismiss the alert
    public void dismissAlert() {
        try {
            SharedSD.getDriver().switchTo().alert().dismiss(); //
            Thread.sleep(3000);
            System.out.println("Alert dismissed.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is no alert");

        }
    }

    //Get text from the alert
    public void getTextAlert() {
        try {
            String alertMessage = SharedSD.getDriver().switchTo().alert().getText();
            Thread.sleep(3000);
            System.out.println("Alert message: " + alertMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is no alert");
        }
    }

    //Enters text to the alert
    public void sendKeysToAlert(String text) {
        try {
            SharedSD.getDriver().switchTo().alert().sendKeys(text);
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //getDriver().findElement(locator).sendKeys(text); ????? is it possible to have an alert where we have to locate element
        System.out.println("There is no alert");
    }

    //Switches to frame by index
    public void switchToFrame(int index) {
        try {
            SharedSD.getDriver().switchTo().frame(index);
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Screen shot should be taken");

        }
    }

    //Switches to frame by name
    public void switchToFrame(String name) {
        try {
            SharedSD.getDriver().switchTo().frame(name);
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Screen shot should be taken");

        }
    }

    //Switches to frame by WebElement
    public void switchToFrame(WebElement webElement) {
        try {
            SharedSD.getDriver().switchTo().frame(webElement);
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Screen shot should be taken");

        }
    }

    //Auto complete
    public void autoComplete(List<WebElement> list, String text) {
        for (WebElement ele : list) {
            if (ele.getText().contains(text)) {
                ele.click();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    //Click on element from the list
    public void selectOnElementFromList(List<WebElement> list, String text) {
        for (WebElement ele : list) {
            if (ele.getText().equalsIgnoreCase(text)) {
                ele.click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    //Looks for specified element in the list
    public boolean isElementInTheList(List<WebElement> list, String text) {
        boolean isFound = false;
        for (WebElement ele : list) {
            if (ele.getText().contentEquals(text)) {
                System.out.println("Element is presented: " + text);
                isFound = true;
                break;
            }
        }
        return isFound;
    }


    //Hovers over element
    public static void mouseOverElement(By overLocator) throws InterruptedException {
        WebElement element = findAndWaitOfWebElement(overLocator);
        //Create action instance
        Actions action = new Actions(SharedSD.getDriver());
        action.moveToElement(element).build().perform();
        Thread.sleep(5000);

    }

    public void clickOnBrowserBackArrow() {
        SharedSD.getDriver().navigate().back();
    }

    public void clickOnBrowserForwardArrow() {
        SharedSD.getDriver().navigate().forward();
    }

    public void refreshBrowser() {
        SharedSD.getDriver().navigate().refresh();
    }

    //Scroll on the page
    public static void scrollOnThePage() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) SharedSD.getDriver();
        //Vertical scroll down 150 pixels
        js.executeScript("window.scrollBy(0,150)");
        Thread.sleep(10000);
    }

    /**
     * Wait block
     */

    //Implicitly wait
    public static WebElement implicitlyWaite(String url, By locator, int waitingTime) {
        SharedSD.getDriver().manage().timeouts().implicitlyWait(waitingTime, TimeUnit.SECONDS);
        SharedSD.getDriver().get(url);
        WebElement element = SharedSD.getDriver().findElement(locator);
       return element;
    }

    //Finds and return web element and wait certain time during process
    public static WebElement findAndWaitOfWebElement(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotFoundException.class)
                .ignoring(java.util.NoSuchElementException.class)
                .withMessage(" Web driver waited,  element could not be found, Exception has been thrown");

        WebElement element = wait.until(new Function<WebDriver,WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

    //Finds list of web element and wait certain time during process
    public static List <WebElement> findAndWaitOfWebElements(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotFoundException.class)
                .ignoring(java.util.NoSuchElementException.class)
                .withMessage(" Web driver waited,  element could not be found, Exception has been thrown");

        List <WebElement> elements = wait.until(new Function<WebDriver,List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(locator);
            }
        });
        return elements;
    }

    //Expected waite, timeout 10 sec
    public static void wateUntilElementClicable(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(SharedSD.getDriver(), seconds);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Waite until page loading
    public static void pageLoadingWait(long timeInSecond) {
        SharedSD.getDriver().manage().timeouts().pageLoadTimeout(timeInSecond, TimeUnit.SECONDS);
    }

    //Script timeout
    public static void asynchronusScript(int timeInSecond) {
        SharedSD.getDriver().manage().timeouts().setScriptTimeout(timeInSecond, TimeUnit.SECONDS);
    }

    //Expected waite, timeout 10 sec
    public static void wateUntilElementPresent(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(SharedSD.getDriver(), seconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Click on element using js executor
    public static void clickOnElementByJs(By locator) throws InterruptedException {
        WebElement element = findAndWaitOfWebElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) SharedSD.getDriver();
        js.executeScript("arguments[0].click();", element);
        Thread.sleep(3000);
    }


    //Sets driver browser window
    public static void setChromeBrowserWindow() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=800,480");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //Opens browser window with set size;
        WebDriver driver = new ChromeDriver(capabilities);
    }

    //Sets driver browser window to full screen
     public static void fullscreenWindow(){
         SharedSD.getDriver().manage().window().maximize();

    }
}
