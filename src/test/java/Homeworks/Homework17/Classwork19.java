package Homeworks.Homework17;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Classwork19 extends BaseTest {
    private final String guruUrl = "https://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    private final String login = "1303";
    private final String pass = "Guru99";


    @Test
    public void GuruPageTest() {
        driver.get(guruUrl);
        String alertText = "You Have Succesfully Logged Out!!";

        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("btnLogin")).click();

        WebElement pageTitle = driver.findElement(By.className("barone"));
        WebElement expectedTitle = driver.findElement(By.className("Guru99 Bank"));
        Assert.assertEquals(pageTitle, expectedTitle);
        driver.findElement(By.cssSelector("[href^=Logout]")).click();

        Assert.assertEquals(driver.switchTo().alert().getText(), alertText);
        Assert.assertTrue(driver.switchTo().alert().getText().contains(alertText));

        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.getCurrentUrl(), guruUrl);

    }
}
