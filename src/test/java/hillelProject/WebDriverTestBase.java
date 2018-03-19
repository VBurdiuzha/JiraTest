package hillelProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import hillelProject.reporting.TestRail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class WebDriverTestBase {
    protected static WebDriver browser;
//    private TestRail trReport;

    {
        System.setProperty("webdriver.chrome.driver", "/Users/villiburduza/IdeaProjects/chromedriver");
    }

    @BeforeTest
    public static void setUp() throws MalformedURLException {
        browser = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized", "--incognito"));
        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
// new RemoteWebDriver(new URL("http://192.168.3.249:9515"), DesiredCapabilities.chrome());  // URL локальной сети, чей комп
// на компе получателя chromedriver --whitelisted-ips="192.168.1.44"

        hillelProject.WebDriverTools.setDriver(browser);
    }

    @AfterTest
    public static void finish() {

        browser.close();
    }
//    @Parameters ({ "testRailProjectId", "testRailRunPrefix" })
//    @BeforeTest(groups = "TestRailReport")
//    protected void prepareTestRailRun(String projectId, String runPrefix) throws Exception {
//        String baseURL = "https://hillelrob.testrail.io/";
//        System.out.println("Reporting to " + baseURL);
//
//        trReport = new TestRail(baseURL);
//        trReport.setCreds("rvalek@intersog.com", "hillel");
//        trReport.startRun(projectId, runPrefix + new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date()));
//    }
//
//    @AfterMethod(groups = "TestrailReport")
//    protected void reportResult(ITestResult testResult) throws Exception {
//        String testDescription = testResult.getMethod().getDescription();
//        trReport.setResult(Integer.parseInt(testDescription.substring(0, testDescription.indexOf("."))),
//                testResult.getStatus());
//    }
//
//    @AfterClass(groups = "TestrailReport")
//    protected void closeTestRailRun() throws Exception {
//        trReport.endRun();
//    }

}