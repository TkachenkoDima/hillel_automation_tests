package Homeworks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Locale;

import static com.codeborne.selenide.Configuration.headless;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        headless = true;
        Locale.setDefault(new Locale("US"));
        System.setProperty("chromeoptions.args", "--window-size=1920x1080");
        Selenide.open("about:blank");
        WebDriver driver = WebDriverRunner.driver().getWebDriver();
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        setWebDriver(eventDriver);
    }

    @AfterMethod(alwaysRun = true)
    public void killDriver() {
        Selenide.closeWebDriver();
    }
}