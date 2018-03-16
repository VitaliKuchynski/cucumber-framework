package framework.darksky;


import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinition.SharedSD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class HomePage extends BasePage{

    //Initialise variable and assigns today bar element
    private By todayBar = By.xpath(".//div[@id=\"week\"]/descendant::span[position()=1]");

    private By barMinTemp= By.xpath(".//div[@id=\"week\"]/descendant::a[position()=1]//span[@class='minTemp']");
    private By barMaxTemp = By.xpath(".//div[@id=\"week\"]/descendant::a[position()=1]//span[@class='maxTemp']");
    private By lowDetaledTem = By.xpath("//*[@id=\"week\"]/div[2]/div[1]/div[2]/div[1]/span[1]/span[1]");
    private By highDetalledTem = By.xpath("//*[@id=\"week\"]/div[2]/div[1]/div[2]/div[1]/span[3]/span[1]");
    //Initialises variable for Time machine button
    private By timeMachineButton =By.xpath(".//div[@id='timeMachine']/descendant::a");
    //Initialises variable for
    private By currentMonthDays = By.xpath("//*[@class='pika-table']/tbody/descendant::td");
    private By dateTitle = By.xpath(".//div[@class='date']");

    //Initialises List collection for list of month days
    private List<WebElement> listOfMonthDays;

    //Initialise variable and assigns list of week days
    private List<WebElement> listOfWeekDays = SharedSD.getDriver().findElements(By.xpath(".//div[@id=\"week\"]/descendant::span[@class='name']"));
    //Initialise List collection with expected week days
    private List<String> expectedListOfWeekDays = new ArrayList<>();
    //Initialise List collection with actual week days
    private List<String> actualListOfWeekDays = new ArrayList<>();
    Date selectedDate;
    private String expectedFormatDate;
    String day;

    public String getExpectedFormatDate() {
        return expectedFormatDate;
    }

    //Sets list of expected days starting from current date
   public void setListOfExpectedDays(){
       //Create formatter to parse date
       SimpleDateFormat format = new SimpleDateFormat("EEE");
       //Instance of calendar
       Calendar calendar = Calendar.getInstance();
       //Sets current date
       calendar.setTime(new Date());
       //Adds value to 0 index
       expectedListOfWeekDays.add(0,"Today");
       //Iterates n time based on size of listOfWeekDays
       for (int i=1; i<listOfWeekDays.size(); i++){
           //adds a day each iteration
           calendar.add(Calendar.DAY_OF_WEEK, 1);
           //Gets day and format it to String and adds to List each iteration
           expectedListOfWeekDays.add(format.format(calendar.getTime()));
       }
   }

    //Gets text value from listOfWeekDays collection and adds to  actualListOfWeekDays collection
   public void setListOfWeekDaysInString(){
       for (WebElement listOfDays: listOfWeekDays){
           //Adds string value to actualListOfWeekDays collection
           actualListOfWeekDays.add(listOfDays.getText());
       }
   }

    //Compares expectedListOfWeekDays and actualListOfWeekDays, returns boolean value
    public boolean isWeekDaysDisplayedCorrectly(){
       return expectedListOfWeekDays.equals(actualListOfWeekDays);
    }

    //Clicks on today bar
    public void clickOnTodayBar(){
       //clickOn(todayBar);
        selectOnElementFromList(listOfWeekDays,"Today");

    }
    //Compares low and high temperature for
    public boolean isTodayTemperatureDisplayedCorrectly(){
         if(getTextFromElement(barMinTemp).equals(getTextFromElement(lowDetaledTem))){
             if(getTextFromElement(barMaxTemp).equals(getTextFromElement(highDetalledTem))){
                 return true;
             }
         } return false;
    }

    //Clicks on time machine button
    public void clickOnTimeMachineButton(){
        clickOn(timeMachineButton);
    }

    //Sets list of month days
    public void setListOfMonthDays(){
        this.listOfMonthDays = findAndWaitOfWebElements(currentMonthDays);
    }

    //Clicks on tomorrow's date
    public void selectTomorrowDate() throws InterruptedException {
        setListOfMonthDays();
        SimpleDateFormat format = new SimpleDateFormat("d");
        //Instance of calendar
        Calendar calendar = Calendar.getInstance();
        //Sets current date
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        //Gets date in int format and return suffix in string
       day = getDayNumberSuffix(calendar.get(Calendar.DAY_OF_MONTH));
        selectedDate = calendar.getTime();
        String expectedDate =  format.format(selectedDate);
        for(WebElement list: listOfMonthDays){
            if(list.getText().equals(expectedDate)){
                list.click();
                break;
            }
        }

        expectedFormatDate = convertSelectedDateToExpectedFormat();
    }
    //Converts selected date to format
    public String convertSelectedDateToExpectedFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEEE, MMM d'" + day + "', yyyy");
        return formatter.format(selectedDate);
    }

    //Gets selected date in int and based on date returns Suffix in string
    public String getDayNumberSuffix(int day) {
//        if (day >= 11 && day <= 13) {
//            return "th";
//        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
    //Verifies is date title is enabled after was clicked
    public boolean isDateTitleEnabled(){
        clickOn(dateTitle);
        return isElementEnabled(dateTitle);
    }
    //Gets date title in String format
    public String getDateTitleInString(){
        return getTextFromElement(dateTitle);
    }

}
