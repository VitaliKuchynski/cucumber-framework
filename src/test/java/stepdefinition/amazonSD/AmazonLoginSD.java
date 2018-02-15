package stepdefinition.amazonSD;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.amazonPages.AmazonHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import stepdefinition.SharedSD;



public class AmazonLoginSD {

    AmazonHomePage amazonHomePage = new AmazonHomePage();

    @Given("^I am on home page of Amazon$")
    public void imOnHomepage() {
        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
    }

    @When("^I Hover over to (Accounts & List)$")
    public void hoverOver(String account) throws InterruptedException {
        amazonHomePage.mouseoverToSignIn();

    }

    @And("I click on (Sign in|continue) button")
    public void clickOnSignInButton(String button) {
        switch (button) {
            case "Sign in":
                amazonHomePage.clickOnSignInButton();
                break;
            case "continue":
                //Implement Create account object
                amazonHomePage.clickONContinueButton();
                break;
        }


    }

    @And("^I enter invalid (.+) into (email) address")
    public void enterDataIntoTextFields(String anyText, String textFields) {

        switch (textFields) {
            case "email":
                amazonHomePage.enterEmail(anyText);
                break;

        }
    }

    @Then("^I verify invalid error message$")
    public void verifyErrorMessage() throws InterruptedException {
        //Thread.sleep(5000);
        String actualMessage = SharedSD.getDriver().findElement(By.xpath(".//div[@class=\"a-alert-content\"]/descendant::span[@class='a-list-item']")).getText();
        Assert.assertEquals(actualMessage,"We cannot find an account with that email address");
    }
}