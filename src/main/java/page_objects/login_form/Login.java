package page_objects.login_form;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login {

    private By usernameField = By.id("login_form[username]");
    private By passwordField = By.id("login-modal-password-input");

    private By loginButton = By.xpath("//button[contains(@class,'modal-submit-button') and text()='Log In']");

    public void populateUsername(String username){
        Browser.getDriver().findElement(usernameField).sendKeys(username);
    }

    public void populatePasswordField(String password){
        Browser.getDriver().findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        Browser.getDriver().findElement(loginButton).click();
    }
}
