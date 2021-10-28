package UITests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class LoginTest extends BaseUITest {

    @Test
    public void yandexLogin_ValidCredentials() throws MalformedURLException {
        loginPage.login(validLogin, validPassword);
        Assert.assertEquals("User name is not equal to login", validLogin, profilePage.getUserName());
        System.out.println("yandexLogin_ValidCredentials test pass");
    }

    @Test
    public void yandexLogout() throws MalformedURLException {
        loginPage.login(validLogin, validPassword);
        String loginUrl = driver.getCurrentUrl();
        profilePage.userLogout();
        String logoutUrl = driver.getCurrentUrl();
        Assert.assertNotEquals("Url after logout is equal to url before", loginUrl, logoutUrl);
        Assert.assertTrue("Login button is not displayed", loginPage.getLoginBtn().isDisplayed());
        System.out.println("yandexLogout test pass");

    }
}

