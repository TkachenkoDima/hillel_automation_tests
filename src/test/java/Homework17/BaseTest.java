package Homework17;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Locale;

public abstract class BaseTest {

    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        Locale.setDefault(new Locale("US"));
        System.setProperty("chromeoptions.args", "--window-size=1920x1080");
        driver = new ChromeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void killDriver() {
        driver.quit();
    }
}
