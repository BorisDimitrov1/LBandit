package browser;

import com.jayway.jsonpath.JsonPath;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.RequestId;
import org.openqa.selenium.devtools.v127.network.model.Response;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Browser {

    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static DevTools devTools;

    public static Map<String, String> savedValues = new HashMap<String, String>();

    public static void openBrowser() throws Exception {

        switch (System.getProperty("browser")) {
            case "chrome":
                initChrome();
                break;
            //Cases for other browsers can be placed here
            default:
                throw new Exception("No such browser");
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static void initChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to Windows os only
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--start-maximized");


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
    }

    public static void tearDown() {
        Browser.getDriver().quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void interceptResponse(String responseName, String jsonPath, String saveAsKey){
        Browser.devTools = ((ChromeDriver) Browser.getDriver()).getDevTools();
        Browser.devTools.createSession();

        //Enable network monitoring
        Browser.devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        RequestId[] requestId = new RequestId[1];

        Browser.devTools.addListener(Network.responseReceived(), responseConsumer -> {
            Response response = responseConsumer.getResponse();
            //Get the response we are looking for
            if(response.getUrl().contains(responseName)){
                requestId[0] = responseConsumer.getRequestId();

                String responseBody = devTools.send(Network.getResponseBody(requestId[0])).getBody();

                //Get the value with json path
                String valueToSave = JsonPath.read(responseBody, jsonPath).toString();

                //Save the value with key
                savedValues.put(saveAsKey, valueToSave);
            }
        });
    }
}
