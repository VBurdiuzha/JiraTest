package hillelProject.jira;

import hillelProject.WebDriverTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class JiraTest extends WebDriverTestBase {


    private LoginPage loginPage;
    private IssuePage issuePage;
    private UserPage userPage;

    @BeforeClass
    public void initPages() {
        loginPage = PageFactory.initElements(browser, LoginPage.class);
        issuePage = PageFactory.initElements(browser, IssuePage.class);
        userPage = PageFactory.initElements(browser, UserPage.class);
        System.out.println("Jira Pages Initialized");
    }

    @Test(description = "1. Invalid Login", priority = -1, groups = {"disabled"})
    public void failureLogin() {

        loginPage.failureLogin();
    }

    @Test(description = "2. Valid Login", groups = {"Login"})
    public void successfulLogin() {
        loginPage.successfulLogin();
    }

    @Test(description = "3. Create User", dependsOnMethods = {"successfulLogin"}, groups = {"user.Create","Sanity"})
    public void createUser() throws InterruptedException {
        UserPage.createUser();
    }

    @Test(description = "4. find User", dependsOnMethods = {"createUser"}, groups = {"user.Find","Sanity"})
    private void findUser() throws InterruptedException {
        UserPage.findUser();
    }

    @Test(description = "5. delete User", dependsOnMethods = {"findUser"}, groups = {"user.Delete","Sanity"})
    private void deleteUser() throws InterruptedException {
        UserPage.deleteUser();
    }


    @Test(description = "6. Create issue", dependsOnMethods = { "deleteUser" })
    public void createIssue() throws InterruptedException {
        issuePage.createIssue();
    }

    @Test(description = "384. Open issue", dependsOnMethods = { "createIssue" }, groups = { "Issue.Open" })
    public void openIssue() {

        issuePage.openIssue();
    }

    @Test(description = "385. Uplaod Attachment", dependsOnMethods = { "openIssue" }, groups = { "Issue.UploadAttachments"})
    public void uploadAttachment() {

        Assert.assertTrue(issuePage.uploadAttachment());
    }

    @Test(description = "386. Download Attachment", dependsOnMethods = { "uploadAttachment" }, groups = { "Issue.DownloadAttachments" })
    public void downloadAttachment() throws InterruptedException, NoSuchAlgorithmException, IOException {
        issuePage.downloadAttachment();
    }

}