package step_definitions.login_form;

import browser.Browser;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.login_form.Login;
import java.time.Duration;


public class LoginStepDefinitions {

    Login loginForm = new Login();

    @And("I populate username field with {string}")
    public void populateUsernameField(String username){
        loginForm.populateUsername(username);
    }

    @And("I populate password field with {string}")
    public void populatePasswordField(String password){
        loginForm.populatePasswordField(password);
    }

    @And("I click login button")
    public void clickLoginButton(){


        loginForm.clickLoginButton();
    }

    @And("I close any modals")
    public void closeModals() {

        By modalCloseButton = By.id("someId");

        //I haven't seen modal after the login so this an example of how I would do it
        try {
            new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(modalCloseButton)).click();
        } catch (Exception e) {
            // No modal appeared, ignore
        }

        /**
         * based on how the modal is presented it can have different implementations
         * 1.Alert class from org.openqa.selenium can be used to close is
         * 2. Action class from import org.openqa.selenium.interactions can also be used to send ESC key;
         */
    }
}
