package hillelProject;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverTools {
    private static WebDriver browser;

    public static void setDriver(WebDriver browser) {
        WebDriverTools.browser = browser;
    }

    public static WebElement clearAndFill(By selector, String data) {
        WebElement element = browser.findElement(selector);
        element.clear();
        element.sendKeys(data);

        return element;
    }

    public static String timestamp() {

        return new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date());
    }
}