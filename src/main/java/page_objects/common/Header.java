package page_objects.common;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page_objects.LandingPage;

public class Header {

    private By loginButton = By.xpath("//button[contains(@class,'cl-login-button')]");
    private By balanceValue = By.xpath("//span[text()='Balance']//..//span[@class='user-balance-item-amount']");

    public void clickLoginButton(){
        LandingPage.waitForLoaderToDisappear();
        Browser.wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getBalance(){
        return Browser.wait.until(ExpectedConditions.visibilityOfElementLocated(balanceValue)).getText();
    }
}
