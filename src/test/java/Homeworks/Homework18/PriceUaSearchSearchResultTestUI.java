package Homeworks.Homework18;

import Homeworks.UIBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriceUaSearchSearchResultTestUI extends UIBaseTest {
    private final String url = "https://price.ua/ua";
    private final String searchPhrase = "oculus";
    private final String expectedElementTitle = "Oculus Quest 2 256 Gb";

    @Test
    public void PriceUaSearchTest () {
        driver.get(url);
        WebElement searchField = driver.findElement(By.cssSelector("#SearchForm_searchPhrase"));
        searchField.sendKeys(searchPhrase);
        WebElement searchButton = driver.findElement(By.cssSelector("#search-block-submit"));
        searchButton.click();
        WebElement foundElementTitle = driver.findElement(By.cssSelector("a[title^='Oculus Quest 2 256 Gb'"));
        Assert.assertTrue(foundElementTitle.isDisplayed(),
                "Search product not displayed!");
        Assert.assertEquals(foundElementTitle.getAccessibleName(), expectedElementTitle,
                "Search product not as expected");
    }
}
