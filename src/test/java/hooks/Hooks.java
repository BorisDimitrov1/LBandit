package hooks;

import browser.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    //Executed before every scenario
    @Before
    public static void beforeScenario() throws Exception {
        Browser.openBrowser();
    }

    //Executed after every scenario
    @After
    public static void afterScenario() {
        Browser.tearDown();
    }
}
