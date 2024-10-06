package step_definitions.common;

import browser.Browser;
import io.cucumber.java.en.And;
import org.junit.Assert;
import page_objects.common.Header;

public class HeaderStepDefinitions {

    private Header headerPage = new Header();

    @And("I click login button in the header of the page")
    public void clickLoginButton(){
        headerPage.clickLoginButton();
    }

    @And("I validate the balance shown in the header is one saved as {string}")
    public void iValidateTheBalanceShownInTheHeaderIsTheSameAs(String key) {
        Assert.assertEquals(Browser.savedValues.get(key), headerPage.getBalance());
    }
}
