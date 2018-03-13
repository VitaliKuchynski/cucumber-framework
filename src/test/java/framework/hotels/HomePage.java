package framework.hotels;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinition.SharedSD;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomePage extends BasePage{

    //Initialise variable, assigns home page title
    private String expectedTitleHomePage = "Hotels.com - Cheap Hotels, Discount Rates & Hotel Deals";
    //Initialise variable, assigns search field location
    private By searchFiled = By.xpath(".//div[@class='widget-query-group widget-query-destination']/descendant::input");
    //Initialise variable, assigns close link
    private By closePoUpLink = By.xpath(".//div[@id='managed-overlay']/button");
    //Initialise variable, assigns Check in date filed
    private By checkInField = By.xpath(".//div[@class='widget-query-date']/label[@id='widget-query-label-2']");
    //Initialise variable, assigns Check out date field
    private By checkOutField = By.xpath(".//div[@class='widget-query-date']/label[@id='widget-query-label-4']");
    //Initialise variable, assigns rooms dropdown
    private By roomsDropdown = By.id("qf-0q-compact-occupancy");
    //Initialise variable, assigns all elements of Rooms dropdown
    private By roomsDropdownOptions = By.xpath(".//select[@id='qf-0q-compact-occupancy']/descendant::option[position()=3]");
    //Initialise variable, assigns adults dropdown
    private By adultsDropdown = By.xpath(".//div[@class='widget-query-adults']/select");
    //Initialise variable, assigns all options of adults dropdown
    private By adultsDropdownOptions = By.xpath(".//div[@class='widget-query-adults']/descendant::option");
    //Initialise variable, assigns rooms dropdown
    private By roomsNumberOfDropdown = By.xpath(".//div[@class='widget-query-room-number']/select");
    //Initialise variable, assigns all elements of rooms dropdown
    private By numberOfRoomsDropdownOptions = By.xpath(".//div[@class='widget-query-room-number']/descendant::option");
    //Initialise variable, assigns child dropdown
    private By childrenDropdown = By.xpath(".//div[@class='widget-query-children']/descendant::select[position()=1]");
    //Initialise variable, assigns all elements of children dropdown
    private By childrenDropdownOptions = By.xpath(".//div[@class='widget-query-children']/descendant::option");
    //Initialise variable, assigns first child dropdown
    private By firstChildDropdown = By.xpath(".//div[@class='widget-query-room-options-children']/descendant::select[@id='qf-0q-room-0-child-0-age']");
    //Initialise variable, assigns second child dropdown
    private By secondChildDropdown = By.xpath(".//div[@class='widget-query-room-options-children']/descendant::select[@id='qf-0q-room-0-child-1-age']");
    //Initialise variable, assigns all elements first child dropdown
    private By firstChildDropdownOptions = By.xpath(".//div[@class='widget-query-room-options-children']/descendant::select[@id='qf-0q-room-0-child-0-age']/option");
    //Initialise variable, assigns all elements second child dropdown
    private By secondChildDropdownOptions = By.xpath(".//div[@class='widget-query-room-options-children']/descendant::select[@id='qf-0q-room-0-child-1-age']/option");
    //Initialise variable, assigns search button
    private By searchButton = By.xpath("//div[@class='widget-query-group widget-query-ft']/button");
    //Initialise variable, assigns all elements of children dropdown
    private By searchResultCityDetails = By.xpath(".//div[@id='search']/descendant::div[@class='summary']/h1");
    //Initialise variable, assigns people and room search result
    private By searchResultRoomsPeopleDetails = By.xpath(".//div[@class='dates-occupancy']/descendant::span[position()=3]");
    //Initialise variable, assigns number of nights after search
    private By searchResultNightsDetails = By.xpath(".//div[@class='dates-occupancy']/descendant::span[position()=2]");
    //Initialise variable, assigns dates details search
    private By searchResultDatesDetails = By.xpath(".//div[@class='dates-occupancy']/descendant::span[position()=1]");
    //Initialise collection days year
    private List<WebElement> listYearDays;
    //Initialise collection list of rooms options
    private List<WebElement> listOfRoomsOptions;
    // Check in date
    private Date checkInDate = null;
    //Initialise Check out date
    private Date checkOutDate = null;
    //Initialise variable and assign period between check in out dates
     private String checkInOutPeriodResult = null;
    //Getters for period of time between check in/out
    public String getCheckInOutPeriodResult() {
        return checkInOutPeriodResult;
    }

    //Getter expected for title
    public String getExpectedTitleHomePage() {
        return expectedTitleHomePage;
    }
    //Gets actual hotels home page title
    public String getActualTitleHotelsHome(){
        return getTitle();
    }
    //Enters text to search field and select result from suggestion list
    public void enterTextToSearchField(String text) throws InterruptedException {
        clickOn(closePoUpLink); //need to be modified
        sendText(searchFiled,text);
        Thread.sleep(3000);
        List<WebElement> listOfAutosSuggest = findAndWaitOfWebElements(By.xpath(".//div[@class='autosuggest-category-result']"));
        autoComplete(listOfAutosSuggest,"San Francisco, California, United States ");
    }
    //Gets days from calendar and assigns to collection
    public void setListYearDays(){
        this.listYearDays = SharedSD.getDriver().findElements(By.xpath(".//div[@class='widget-datepicker-bd']/descendant::td"));
    }
    //Clicks on Check in label
    public void clickOnCheckInField(){
        asynchronusScript(5);
        clickOn(checkInField);
    }
    //Selects current date in Check in field
    public void selectCheckInDate() {
        clickOnCheckInField();
        setListYearDays();
        //selectCurrentDate(listYearDays);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        //Instance of calendar
        Calendar calendar = Calendar.getInstance();
        //Sets current date
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        checkInDate = calendar.getTime();
        String expectedDate =  format.format(checkInDate);
        for(WebElement list: listYearDays){
            if(list.getText().equals(expectedDate)){
                list.click();
                break;
            }
        }
    }
    //Selects Check out date
    public void selectCheckOutDate(){
        clickOn(checkOutField);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        //Instance of calendar
        Calendar calendar = Calendar.getInstance();
        //Sets current date
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);
        checkOutDate = calendar.getTime();
        String expectedDate =  format.format(checkOutDate);
        for(WebElement list: listYearDays){
            if(list.getText().equals(expectedDate)){
                list.click();
                break;
            }
        }
        //Sets period of between check in check out dates and returns it in string format based on check out date
        setCheckInOutDatesPeriod();
        System.out.println(checkInOutPeriodResult);

    }
    //Sets period between check in check out and converts it in string format based on month
    public String setCheckInOutDatesPeriod(){
        SimpleDateFormat convert = new SimpleDateFormat("MMMMM");
        Date current =new Date();
        String currentMonth = convert.format(current);
        String checkOutMonth = convert.format(checkOutDate);
        //if statement compare months
        if(currentMonth.equalsIgnoreCase(checkOutMonth)){
            SimpleDateFormat formatter1 = new SimpleDateFormat("EEE dd");
            SimpleDateFormat formatter2 = new SimpleDateFormat("EEE dd MMMMM YYYY");
            String checkInDateResult = formatter1.format(checkInDate);
            String checkOutDateResult = formatter2.format(checkOutDate);
            checkInOutPeriodResult = checkInDateResult +" - "+ checkOutDateResult;
        }else {
            SimpleDateFormat formatter1 = new SimpleDateFormat("EEE dd MMMMM");
            SimpleDateFormat formatter2 = new SimpleDateFormat("EEE dd MMMMM YYYY");
            String checkInDateResult = formatter1.format(checkInDate);
            String checkOutDateResult = formatter2.format(checkOutDate);
            checkInOutPeriodResult = checkInDateResult +" - "+ checkOutDateResult;
        }
        return checkInOutPeriodResult;
    }

    //Clicks on rooms dropdown options
    public void clickOnRoomsDropdown(){
        clickOn(roomsDropdown);
    }
    //Selects more options from rooms dropdown list
    public void selectMoreOptionsRomsDropdown(){
       listOfRoomsOptions = findAndWaitOfWebElements(roomsDropdownOptions);
       selectOnElementFromList(listOfRoomsOptions,"More options…");
    }
    //Selects number of adults
    public void selectAdults(String number){
        clickOn(adultsDropdown);
        List<WebElement> listOfAdultsValues = findAndWaitOfWebElements(adultsDropdownOptions);
        selectOnElementFromList(listOfAdultsValues,number);

    }
    //Selects number of rooms
    public void selectRooms(String number){
        clickOn(roomsNumberOfDropdown);
        List<WebElement> listOfRoomsValues = findAndWaitOfWebElements(numberOfRoomsDropdownOptions);
        selectOnElementFromList(listOfRoomsValues,number);
    }
    //Selects number of children
    public void selectChildren(String number){
        clickOn(childrenDropdown);
        List<WebElement> listOfChildrenValues = findAndWaitOfWebElements(childrenDropdownOptions);
        selectOnElementFromList(listOfChildrenValues,number);
    }
    //Selects child age
    public void selectChildAge (String child,String number){
        //switches between drop-downs
        switch (child){
            case"first":
          clickOn(firstChildDropdown);
        List<WebElement> listOfFirstChildAges = findAndWaitOfWebElements(firstChildDropdownOptions);
        selectOnElementFromList(listOfFirstChildAges,number);
            break;
            case"second":
          clickOn(secondChildDropdown);
        List<WebElement> listOfSecondChildAges = findAndWaitOfWebElements(secondChildDropdownOptions);
        selectOnElementFromList(listOfSecondChildAges,number);
            break;
        }
    }
    //Clicks on search button
    public void clickOnSearchButton(){
        clickOn(searchButton);
    }

    //Gets text from search result
    public String getTextFromSearchResult(){
       return getTextFromElement(searchResultCityDetails);
    }
    //Gets string from search results
    public String getSearchResultText(String result) {
    String actualResult = null;
    //Switches between search results
        switch (result) {
            case "dates":
        actualResult = getTextFromElement(searchResultDatesDetails);
                break;
            case "rooms, people":
         actualResult = getTextFromElement(searchResultRoomsPeopleDetails);
                break;
            case "nights":
        actualResult = getTextFromElement(searchResultNightsDetails);
                break;
            default:
        actualResult = null;
        }

        return actualResult;
    }
}
