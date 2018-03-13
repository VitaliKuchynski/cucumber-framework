package stepdefinition.darksky;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.darksky.HomePage;
import org.testng.Assert;
import stepdefinition.SharedSD;

public class DarkskySD {

    HomePage homePage = new HomePage();

    @Given ("^I am on Dark sky home page$")
    public  void iAmOnHomePage(){
        Assert.assertEquals(SharedSD.getDriver().getTitle(),"Dark Sky - 260 Broadway, New York City, NY");
    }

    @Then("^I verify days of the week is displayed in correct order$")
    public void isWeekDasDisplayedInCorrectOrder(){
        boolean expectedResult = homePage.isWeekDaysDisplayedCorrectly();
        Assert.assertEquals(true, expectedResult);
    }

    @Then ("^I verify low and high temperature displayed correctly on parent bar$")
    public void isTemperatureDisplayedCorrectly(){
        Assert.assertEquals(true,homePage.isTodayTemperatureDisplayedCorrectly());

    }
    @Then ("^I verify selected date is not clickable$")
    public  void isSelectedDateNotClickable(){

        Assert.assertEquals(true, homePage.isDateTitleEnabled());

    }

    @When ("^I click on (today bar|time machine$)")
    public void clickOnElement(String element){
        switch (element){
            case "today bar":
             homePage.clickOnTodayBar();
             break;
            case "time machine":
             homePage.clickOnTimeMachineButton();
             break;
    }
}
    @And ("^I select tomorrow date$")
    public void selectTomorrowDate() throws InterruptedException {
        homePage.selectTomorrowDate();
    }

    @And ("^I verify date is displayed in correct format$")
    public void isDateDisplayedCorrectly(){
        String expectedResult ="Sunday, Mar 11th, 2018";
        Assert.assertEquals(homePage.getDateTitleInString(),expectedResult);

    }
}
