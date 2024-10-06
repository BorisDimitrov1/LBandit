package step_definitions.browser;

import browser.Browser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class BrowserStepDefinitions {

    @When("I navigate to LBandit")
    public void navigateToLBandit() {
        Browser.getDriver().navigate().to(System.getProperty("environmentUrl"));
    }


    @And("I get the value from {string} body with path {string} and save it as {string}")
    public static void getValueFromBodyAndSaveIt(String endPoint, String jsonPath, String key) {
        Browser.interceptResponse(endPoint, jsonPath, key);
    }
}
