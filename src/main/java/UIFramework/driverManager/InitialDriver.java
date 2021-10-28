package UIFramework.driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class InitialDriver {

    private static InitialDriver instance = null;
    //private WebDriver driver;

    public RemoteWebDriver remoteDriver;
    private static final String SELENIUM_URL = "http://127.0.0.1:4444/wd/hub";

    private InitialDriver() {
    }

    public static InitialDriver getInstance() {
        if (instance == null) {
            instance = new InitialDriver();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws MalformedURLException {
        if (remoteDriver == null) {
            remoteDriver = new RemoteWebDriver(
                    new URL(SELENIUM_URL),
                    new ChromeOptions()
            );
            //driver = new ChromeDriver();
            //System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
            return remoteDriver;
        } else {
            return remoteDriver;
        }
    }

    public void destroy() throws MalformedURLException {
        if (remoteDriver != null) {
            getDriver().quit();
            remoteDriver = null;
        }
    }
}
