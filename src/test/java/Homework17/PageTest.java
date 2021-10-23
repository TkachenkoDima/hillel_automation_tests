package Homework17;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class PageTest extends BaseTest {
    private final String lmsURL = "https://lms.ithillel.ua/";
    private final String googleURL = "https://www.google.com/";
    private final String lmsTitle = "Hillel LMS | Система личных кабинетов Студентов Компьютерной школы Hillel";

    @Test
    public void LMSPageTest() {
        correctPageTitle();
        wrongPageTitle();
        urlEquals();
        urlNotEquals();
        login();
    }

    private void correctPageTitle() {
        driver.get(lmsURL);
        Assert.assertTrue(driver.getTitle().contains("LMS"),
                "Wrong page title");

    }

    private void wrongPageTitle() {
        driver.get(googleURL);
        Assert.assertFalse(driver.getTitle().contains("LMS"),
                "Wrong page title");
    }

    private void urlEquals() {
        driver.get(lmsURL);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(googleURL);
        Assert.assertEquals(driver.getCurrentUrl(), googleURL,
                "URL not equals");
    }

    private void urlNotEquals() {
        driver.get(lmsURL);
        driver.switchTo().newWindow(WindowType.TAB);
        Assert.assertNotEquals(driver.getCurrentUrl(), googleURL,
                "URL not equals");
    }

    private void login () {
        driver.get(lmsURL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("demitry.tkachenko@gmail.com");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Qweasd12");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.getTitle(), lmsTitle,
                "Not expected page title after login");
    }
}
