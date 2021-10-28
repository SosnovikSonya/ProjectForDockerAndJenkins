package UIFramework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import UIFramework.utils.CustomWaiters;
import UIFramework.utils.PageObject;

import java.net.MalformedURLException;

public class ProfilePage extends PageObject {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class, 'personal-info-login__text')]")
    private WebElement userLoginName;

    @FindBy(xpath = "//span[contains(text(), 'Выйти')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[contains(@class, 'user-account__ticker')]/preceding-sibling::div")
    private WebElement accountInfoIcon;

    public String getUserName() throws MalformedURLException {
        CustomWaiters customWaiters = new CustomWaiters();
        customWaiters.waitUntilVisible(userLoginName);
        return userLoginName.getText();
    }

    public void ClickOnAccountInfoIcon() throws MalformedURLException {
        CustomWaiters customWaiters = new CustomWaiters();
        customWaiters.waitUntilClickable(accountInfoIcon);
        accountInfoIcon.click();
    }

    public void userLogout() throws MalformedURLException {
        ClickOnAccountInfoIcon();
        CustomWaiters customWaiters = new CustomWaiters();
        customWaiters.waitUntilClickable(logoutBtn);
        logoutBtn.click();
    }
}


