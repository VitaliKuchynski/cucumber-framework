package framework.amazon;


import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinition.SharedSD;

import java.util.List;

public class HomePage extends BasePage {

    //Variables with page element
    private By mouseOverAccountsList = By.cssSelector("#nav-link-accountList");
    private By sighInButton = By.xpath(".//div[@class=\'nav-flyout-content\']/descendant::span");
    private By emailTextField = By.xpath("//*[@id=\"ap_email\"]");
    private By continueButton =By.id("continue");
    private By accountAndListLink = By.cssSelector("#nav-link-accountList");
    private By createAccountSubmit = By.id("createAccountSubmit");

    //Mouse  over to Account&List link
    public void mouseOverAccountList() throws InterruptedException {
        mouseOverElement(mouseOverAccountsList);

    }
    //Clicks on SignIn button in pop up menu
    public void clickOnSignInButton() throws InterruptedException {
        clickOnElementByJs(sighInButton);
        //clickOn(sighInButton);
    }
    // Enters value to email input field
    public void enterEmail(String enterEmail) {
        sendText(emailTextField, enterEmail);
    }

    //Clicks on continue button
    public  void clickONxContinueButton(){
        clickOn(continueButton);
    }

    //
    public void clickOnAccountsAdnListLink(){
        clickOn(accountAndListLink);
    }

    public void clickOncreateAccountSubmit(){
        clickOn(createAccountSubmit);
    }



}
