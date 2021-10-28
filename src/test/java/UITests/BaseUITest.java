package UITests;

import UIFramework.driverManager.InitialDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import UIFramework.pages.LoginPage;
import UIFramework.pages.ProfilePage;
import UIFramework.utils.TestData;

import java.net.MalformedURLException;

abstract class BaseUITest extends TestData {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProfilePage profilePage;

    @BeforeEach
    public void BaseTestSetUp() throws MalformedURLException {
        driver = InitialDriver.getInstance().getDriver();
        driver.navigate().to(yandexLoginUrl);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void BaseTestTearDown() throws MalformedURLException {
        InitialDriver.getInstance().destroy();
    }
}