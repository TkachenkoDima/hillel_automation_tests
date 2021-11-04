package Homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Locale;

public abstract class BaseTest {

    public WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Locale.setDefault(new Locale("US"));
        System.setProperty("chromeoptions.args", "--window-size=1920x1080");
    }

    @AfterTest(alwaysRun = true)
    public void killDriver() {
        driver.quit();
    }
}