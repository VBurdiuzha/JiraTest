package hillelProject.jira;

import hillelProject.WebDriverTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;
import static org.openqa.selenium.By.cssSelector;
public class UserPage {

    @FindBy(css = "#admin_menu")
    private WebElement adminMenu;
    @FindBy(css = "#admin_users_menu")
    private static WebElement admUserMenu;
    @FindBy(css = "#login-form-submit")
    private WebElement loginFormSubmit;
    @FindBy(css = "#create_user")
    private WebElement createUser;
    @FindBy(css = "#user-create-email")
    private WebElement createEmail;
    @FindBy(css = "#user-create-fullname")
    private WebElement userCreateFullName;
    @FindBy(css = "#user-create-username")
    private WebElement createUsername;
    @FindBy(css = "#password")
    private WebElement createUserPassw;
    @FindBy(css = "#sendEmail")
    private WebElement sendEmail;
    @FindBy(css = "#user-create-submit")
    private WebElement submitUser;
    @FindBy(css = "#user-filter-userSearchFilter")
    private WebElement userFilter;
    @FindBy(css = "#user_browser_table > tbody > tr > td:nth-child(2)")
    private WebElement filterList;
    @FindBy(css = "#VilliTest > span.fn")
    private WebElement clickName;
    @FindBy(css = "#content > div.aui-page-panel > div > section > header > div > div.aui-page-header-actions > div:nth-child(2) > a")
    private WebElement clickActions;
    @FindBy(css = "#deleteuser_link")
    private WebElement userDelete;
    @FindBy(css = "#delete_user_confirm-submit")
    private WebElement userDeleteYes;
    @FindBy(css = "#logo > a > img")
    private WebElement Logo;

    private WebElement createSubmit;

    private static WebDriver browser;

    public UserPage(WebDriver browser) {

        this.browser = browser;
    }


    public static void createUser() throws InterruptedException {

        browser.findElement(cssSelector("#admin_menu")).click();

        browser.findElement(cssSelector("#admin_users_menu")).click();

        WebDriverTools.clearAndFill(cssSelector("#login-form-authenticatePassword"), JiraVars.password);

        browser.findElement(cssSelector("#login-form-submit")).click();

        browser.findElement(cssSelector("#create_user")).click();

        WebDriverTools.clearAndFill(cssSelector("#user-create-email"), JiraVars.userEmail);

        WebDriverTools.clearAndFill(cssSelector("#user-create-fullname"), JiraVars.userName);

        WebDriverTools.clearAndFill(cssSelector("#user-create-username"), JiraVars.userNick);

        WebDriverTools.clearAndFill(cssSelector("#password"), JiraVars.userPass);

        browser.findElement(cssSelector("#sendEmail")).click();

        browser.findElement(cssSelector("#user-create-submit")).click();


    }

    public static void findUser() throws InterruptedException{
        WebDriverTools.clearAndFill(cssSelector("#user-filter-userSearchFilter"), JiraVars.userEmail).click();

        List<WebElement> newIssueLinks1 = browser.findElements(cssSelector("#user_browser_table > tbody > tr > td:nth-child(2)"));
        Assert.assertTrue(newIssueLinks1.size() != 0);
    }

    public static void deleteUser() throws InterruptedException{
        browser.findElement(cssSelector("#VilliTest > span.fn")).click();

        browser.findElement(cssSelector("#content > div.aui-page-panel > div > section > header > div > div.aui-page-header-actions > div:nth-child(2) > a")).click();

        browser.findElement(cssSelector("#deleteuser_link")).click();

        browser.findElement(cssSelector("#delete_user_confirm-submit")).click();

        WebDriverTools.clearAndFill(cssSelector("#user-filter-userSearchFilter"), "villy.burdiuzha@gmail.com\n");

        List<WebElement> newIssueLinks5 = browser.findElements(cssSelector("#user_browser_table > tbody > tr > td:nth-child(2)"));
        Assert.assertTrue(newIssueLinks5.size() == 0);
        browser.findElement(cssSelector("#logo > a > img")).click();


    }
}