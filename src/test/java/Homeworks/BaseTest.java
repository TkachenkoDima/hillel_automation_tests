package Homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Locale;

public abstract class BaseTest {

    JavascriptExecutor js;
    protected Actions actions;
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        js = (JavascriptExecutor)driver;
        Locale.setDefault(new Locale("US"));
        System.setProperty("chromeoptions.args", "--window-size=1920x1080");
    }

    @AfterTest(alwaysRun = true)
    public void killDriver() {
        driver.quit();
    }
}