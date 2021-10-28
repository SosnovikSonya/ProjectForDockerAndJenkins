package UIFramework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import UIFramework.utils.CustomWaiters;
import UIFramework.utils.PageObject;

import java.net.MalformedURLException;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='passp-field-login']")
    private WebElement loginInput;

    @FindBy(xpath = "//*[@id='passp-field-passwd']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement loginBtn;

    public void inputLogin(String login) throws MalformedURLException {
        CustomWaiters customWaiters = new CustomWaiters();
        customWaiters.waitUntilVisible(loginInput);
        loginInput.sendKeys(login);
    }

    public void inputPassword(String password) throws MalformedURLException {
        CustomWaiters customWaiters = new CustomWaiters();
        customWaiters.waitUntilClickable(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void clickLoginBtn() throws MalformedURLException {
        CustomWaiters customWaiters = new CustomWaiters();
        customWaiters.waitUntilVisible(loginBtn);
        loginBtn.click();
    }

    public void login(String login, String password) throws MalformedURLException {
        inputLogin(login);
        clickLoginBtn();
        inputPassword(password);
        clickLoginBtn();
    }

    public WebElement getLoginBtn()
    {
        return loginBtn;
    }
}
