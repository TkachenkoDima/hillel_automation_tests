package Homeworks.Homework18;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriceUaSearchSearchResultTest extends BaseTest {
    private final String url = "https://price.ua/ua";
    private final String searchPhrase = "oculus";

    @Test
    public void PriceUaSearchTest () {
        driver.get(url);
        WebElement searchField = driver.findElement(By.cssSelector("#SearchForm_searchPhrase"));
        searchField.sendKeys(searchPhrase);
        WebElement searchButton = driver.findElement(By.cssSelector("#search-block-submit"));
        searchButton.click();
        WebElement myVR = driver.findElement(By.cssSelector("#title-link-4612798"));
        Assert.assertTrue(myVR.isDisplayed(),
                "Search product not displayed!");
        System.out.println(myVR.getAccessibleName() + " is displayed.");
    }
}
