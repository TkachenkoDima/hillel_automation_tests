package Homeworks.Homework18;

import Homeworks.UIBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceDemoTestUI extends UIBaseTest {
    private final String url = "https://www.saucedemo.com";
    private final String login = "standard_user";
    private final String pass = "secret_sauce";
    private final int itemsOnThePage = 6;

    @Test
    public void SauceDemoPageTest() {
        driver.get(url);
        driver.findElement(By.cssSelector("#user-name")).sendKeys(login);
        driver.findElement(By.cssSelector("#password")).sendKeys(pass);
        driver.findElement(By.cssSelector("#login-button")).click();

        List<WebElement> items = driver.findElements(By.cssSelector(".inventory_item"));
        System.out.println("Items on the page: " + items.size());
        Assert.assertEquals(items.size(), itemsOnThePage,
                "Not actual count of items on the page");
    }
}
