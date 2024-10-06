package page_objects;

import browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage {


    private static By loader = By.id("page-loader");

    /**
     * This makes more sense to be part of the landing page when following the page objects design pattern
     * as it will(probably :D) be a global loader
     */
    public static void waitForLoaderToDisappear(){
        Browser.wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

}
