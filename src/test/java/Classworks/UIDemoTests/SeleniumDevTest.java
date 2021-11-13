package Classworks.UIDemoTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumDevTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void killDriver() {
        driver.quit();
    }

    @Test
    public void driverActionsTest() {
        driver.get("https://www.google.com/");
        String googleTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://selenium.dev/");
        Assert.assertEquals(driver.getTitle(), "Selenium",
                "This is not expected page.");

        driver.switchTo().window(googleTab);
        Assert.assertEquals(driver.getTitle(), "Google",
                "not Google");
    }
}
