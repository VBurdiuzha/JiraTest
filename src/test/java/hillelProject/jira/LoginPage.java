package hillelProject.jira;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import hillelProject.WebDriverTools;

public class LoginPage {
    private final By inputUsername = By.cssSelector("input#login-form-username");
    private final By inputPassword = By.cssSelector("input#login-form-password");
    @FindBy(css = "a#header-details-user-fullname")
    private WebElement buttonProfile;
    @FindBy(css = "div#usernameerror")
    private List<WebElement> messageError;

    private final WebDriver browser;

    public LoginPage(WebDriver browser) {

        this.browser = browser;
    }

    public void successfulLogin() {
        login(JiraVars.password);

        Assert.assertEquals(JiraVars.username, buttonProfile.getAttribute("data-username"));
    }

    public void failureLogin() {
        login("badPassword");

        Assert.assertTrue(messageError.size() != 0);
    }

    private void login(String password) {
        browser.get(JiraVars.baseURL);

        WebDriverTools.clearAndFill(inputUsername, JiraVars.username);
        WebDriverTools.clearAndFill(inputPassword, password).submit();
    }

}