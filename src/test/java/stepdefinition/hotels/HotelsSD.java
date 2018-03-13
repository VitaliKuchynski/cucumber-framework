package stepdefinition.hotels;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.hotels.HomePage;
import org.testng.Assert;


public class HotelsSD {
    HomePage homePage = new HomePage();

    @Given("^I am on Hotels home page$")
    public void iAmOnHotelsHomePage() {
        String actualTitle = homePage.getActualTitleHotelsHome();
        String expectedTitle = homePage.getExpectedTitleHomePage();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @When("^I enter (.+) into search field$")
    public void enterCityToSearchField(String city) throws InterruptedException {
        homePage.enterTextToSearchField(city);
    }

    @And("^I select (Check in|Check out) date$")
    public void selectDateFromCalendar(String type) {

        switch (type) {
            case "Check in":
                homePage.selectCheckInDate();
                break;
            case "Check out":
                homePage.selectCheckOutDate();
                break;
        }
    }

    @And("^I select More options$")
    public void selectMoreOptionsLink() {
        homePage.clickOnRoomsDropdown();
        homePage.selectMoreOptionsRomsDropdown();
    }

    @And("^I select (\\d) from (Rooms|Adults|Children) dropdown$")
    public void selectOptions(String number, String options) {
        switch (options) {
            case "Rooms":
                homePage.selectRooms(number);
                break;
            case "Adults":
                homePage.selectAdults(number);
                break;
            case "Children":
                homePage.selectChildren(number);
                break;
        }
    }

    @And("^I select (\\d) year's' for (first|second) child age$")
    public void selectChildrenAge(String age, String child) {
        switch (child) {
            case "first":
                homePage.selectChildAge(child, age);
                break;
            case "second":
                homePage.selectChildAge(child, age);
                break;
        }
    }

    @And("^I click on (Search) button$")
    public void clickOnElement(String element) {
        homePage.clickOnSearchButton();
    }

    @Then("^I verify city search result is displayed correctly$")
    public void verifyCityResultDisplayed() {
        String actualCityResult = homePage.getTextFromSearchResult();
        String expectedCityResult = "San Francisco, California, United States of America";
        Assert.assertEquals(actualCityResult, expectedCityResult);
    }

    @And("^I verify (dates|rooms, people|nights) search result is displayed correctly$")
    public void verifySearchResultDisplayed(String result) {
        switch (result) {
            case "dates":
              String actualPeriod = homePage.getSearchResultText(result);
              String expectedPeriod= homePage.getCheckInOutPeriodResult();
              Assert.assertEquals(actualPeriod,expectedPeriod);
                break;
            case "rooms, people":
        String actualRoomsPeopleResult = "1 room, 2 adults, 2 children";
        Assert.assertEquals(homePage.getSearchResultText(result),actualRoomsPeopleResult);
                break;
            case "nights":
        String actualNightsResult = "6 nights";
        Assert.assertEquals(homePage.getSearchResultText(result),actualNightsResult);
                break;
        }
    }
}