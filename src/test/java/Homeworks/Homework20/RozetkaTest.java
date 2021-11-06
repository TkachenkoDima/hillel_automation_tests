package Homeworks.Homework20;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaTest extends BaseTest {
    private final String url = "https://rozetka.com.ua/";
    private final String headerSearchLocator = "input[name=search]";
    private final String searchInput = "samsung";
    private final String mobilePhonesFiltersLocator = "span[class='categories-filter__link-title ng-star-inserted']:nth-child(1)";

    @Test
    public void filtersTest() {
        driver.get(url);
        driver.findElement(By.cssSelector(headerSearchLocator)).sendKeys(searchInput + Keys.ENTER);
        List<WebElement> mobilePhonesFilter = driver.findElements(By.xpath(mobilePhonesFiltersLocator));
        System.out.println(mobilePhonesFilter.toString());
        for (WebElement mobileFilter : mobilePhonesFilter) {
            mobileFilter.getText();
            if (mobileFilter.getText().contains("Мобильные телефоны")) {
                mobileFilter.click();
                System.out.println("kek");
            }
        }
    }
}


