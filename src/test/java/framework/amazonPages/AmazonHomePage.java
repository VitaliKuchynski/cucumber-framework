package framework.amazonPages;


import framework.BasePage;
import org.openqa.selenium.By;

public class AmazonHomePage extends BasePage {

    //Variables with page element
    private By mouseOverAccountsList = By.xpath("//*[@id=\"nav-link-accountList\"]/span[1]");
    private By sighInButton = By.xpath("//*[@id=\"nav-flyout-ya-signin\"]");
    private By emailTextField = By.xpath("//*[@id=\"ap_email\"]");
    private By continueButton =By.id("continue");

    //Mouse  over to Account&List link
    public void mouseoverToSignIn() throws InterruptedException {
        mouseOverElement(mouseOverAccountsList);

    }
    //Clicks on SignIn button in pop up menu
    public void clickOnSignInButton(){
        clickOn(sighInButton);
    }
    // Enters value to email input field
    public void enterEmail(String enterEmail) {
        sendText(emailTextField, enterEmail);
    }

    //Clicks on continue button
    public  void clickONContinueButton(){
        clickOn(continueButton);
    }

}
