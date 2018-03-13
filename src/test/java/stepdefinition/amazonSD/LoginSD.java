package stepdefinition.amazonSD;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.amazon.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import stepdefinition.SharedSD;



public class LoginSD {

    HomePage homePage = new HomePage();

    @Given("^I am on home page of Amazon$")
    public void imOnHomepage() {
        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
    }

    @When("^I hover over to (Accounts & List)$")
    public void hoverOver(String account) throws InterruptedException {
        //mouse over Account&List element
        homePage.mouseOverAccountList();

    }

    @When("^I click on (Accounts & List link)$")
    public void clickOnAccountsListLink(String button){
        switch (button){
            case "Accounts & List link":
        homePage.clickOnAccountsAdnListLink();
        }
    }



    @And("^I click on (Sign in|continue|Create your Amazon account) button$")
    public void clickOnSignInButton(String button) throws InterruptedException {
        switch (button) {
            case "Sign in":
                homePage.clickOnSignInButton();
                break;
            case "continue":
                //clicks on continue button
                homePage.clickONxContinueButton();
                break;
            case "Create your Amazon account":
                homePage.clickOncreateAccountSubmit();
                break;
        }

    }

    @And("I enter invalid (.+) into (email) address")
    public void enterDataIntoTextFields(String anyText, String textFields) {

        switch (textFields) {
            case "email":
                homePage.enterEmail(anyText);
                break;

        }
    }

    @Then("^I verify invalid error message$")
    public void verifyErrorMessage() throws InterruptedException {
        String actualMessage = SharedSD.getDriver().findElement(By.xpath(".//div[@class=\"a-alert-content\"]/descendant::span[@class='a-list-item']")).getText();
        Assert.assertEquals(actualMessage,"We cannot find an account with that email address");
    }

    @Then("^I verify error message$")
    public void verifyErrorCreateAccountMessage(){
    String actualErrorMessage = SharedSD.getDriver().findElement(By.xpath("//*[@id=\"auth-customerName-missing-alert\"]/div/div")).getAttribute("class");
    Assert.assertEquals(actualErrorMessage,"a-alert-content");
    }
}