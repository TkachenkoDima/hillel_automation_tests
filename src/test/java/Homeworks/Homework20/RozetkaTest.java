package Homeworks.Homework20;

import Homeworks.BaseTest;
import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class RozetkaTest extends BaseTest {
    private final String url = "https://rozetka.com.ua/";
    private final String headerSearchLocator = "input[name=search]";
    private final String searchInput = "samsung";
    private final String mobilePhonesFiltersLocator = "//span[text()='Мобильные телефоны']";
    private final String computerAndLaptopsLocator = "Ноутбуки и компьютеры";
    private final String monitorLocator = "a[title='Мониторы']";
    private final String monitorItemCard = "div.goods-tile__inner";
    private final String appleCheckBox = "label[for='Apple']";
    private final String huaweiCheckBox = "label[for='Huawei']";
    private final String memoryCheckBox = "label[for='128 ГБ']";
    private final String whiteColorCheckBox = "label[for='Белый']";
    private final String minPriceInput = "input[formcontrolname='min']";
    private final String maxPriceInput = "input[formcontrolname='max']";
    private final String submitPriceButton = "button[type='submit']";
    private final String itemPrice = "span.goods-tile__price-value";
    private final String itemTitle = "span.goods-tile__title";

    private final String minPrice = "5000";
    private final String maxPrice = "15000";

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
    }

    @AfterMethod(alwaysRun = true)
    public void postConditions() {
    }

//    @Test
//    public void checkProductBrand() throws InterruptedException {
//        driver.get(url);
//        fillSearchFiled(searchInput);
//        Thread.sleep(1000);
//        clickMobilePhonesCategory();
//        Thread.sleep(1000);
//        clickBrandCheckbox(appleCheckBox);
//        Thread.sleep(1000);
//        clickBrandCheckbox(huaweiCheckBox);
//        Thread.sleep(1000);
//        checkProductTitle();
//
//    }
//
//    @Test
//    public void checkPriceRange() throws InterruptedException {
//        driver.get(url);
//        fillSearchFiled(searchInput);
//        Thread.sleep(1000);
//        clickMobilePhonesCategory();
//        setPrice(minPriceInput, minPrice);
//        setPrice(maxPriceInput, maxPrice);
//        clickSubmitButton();
//        Thread.sleep(1000);
//        checkPrice(minPrice, maxPrice);
//    }
//
//    @Test
//    public void checkFilters() throws InterruptedException {
//        driver.get(url);
//        fillSearchFiled(searchInput);
//        Thread.sleep(1000);
//        clickMobilePhonesCategory();
//        Thread.sleep(1000);
//        clickMemoryCheckBox(memoryCheckBox);
//        Thread.sleep(1000);
//        clickColorCheckBox(whiteColorCheckBox);
//        Thread.sleep(1000);
//
//        checkSelectedFilters();
//
//    }

    @Test
    public void compareProducts() throws InterruptedException {
        driver.get(url);
        driver.findElement(By.linkText(computerAndLaptopsLocator)).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(monitorLocator)).click();
        Thread.sleep(3000);

        List<WebElement> monitors = driver.findElements(By.cssSelector(monitorItemCard));
        for (WebElement monitor : monitors) {
            List<WebElement> monitorPrice = monitor.findElements(By.cssSelector(itemPrice));
            for (WebElement price : monitorPrice) {
                String replacedPrice = price.getText().replaceAll("[^0-9]", "");
                System.out.println(replacedPrice);
                if (Integer.parseInt(replacedPrice) < 5000) {
                    break;
                }
            }
        }
    }

    public void fillSearchFiled(String searchItem) {
        driver.findElement(By.cssSelector(headerSearchLocator)).sendKeys(searchItem + Keys.ENTER);
    }

    public void clickBrandCheckbox(String brand) {
        driver.findElement(By.cssSelector(brand)).click();
    }

    public void clickMobilePhonesCategory() {
        driver.findElement(By.xpath(mobilePhonesFiltersLocator)).click();
    }

    public void clickSubmitButton() {
        driver.findElement(By.cssSelector(submitPriceButton)).click();
    }

    public void clickMemoryCheckBox(String memory) {
        driver.findElement(By.cssSelector(memory)).click();
    }

    public void clickColorCheckBox(String color) {
        driver.findElement(By.cssSelector(color)).click();
    }

    public void clearInputWithKeys(WebElement element){
        if (OS.isFamilyMac()) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            element.sendKeys(Keys.DELETE);
        } else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void setPrice(String priceInput, String price) {
        WebElement minMax = driver.findElement(By.cssSelector(priceInput));
        minMax.click();
        clearInputWithKeys(minMax);
        minMax.sendKeys(price);
    }

    public void checkPrice(String minPrice, String maxPrice) {
        List<WebElement> prices = driver.findElements(By.cssSelector(itemPrice));
        for (WebElement price : prices) {
            String replacedPrices = price.getText().replace(" ", "");
            final boolean replacedMinPrice = Integer.parseInt(replacedPrices) >= Integer.parseInt(minPrice);
            final boolean replacedMaxPrice = Integer.parseInt(replacedPrices) <= Integer.parseInt(maxPrice);
            if (replacedMinPrice && replacedMaxPrice) {
                Assert.assertTrue(true, "Min price is not as expected");
                Assert.assertTrue(true, "Max price is not as expected");
            }
        }
    }

    public void checkSelectedFilters() {
        List<WebElement> items = driver.findElements(By.cssSelector(itemTitle));
        for (WebElement item : items) {
            Assert.assertTrue(item.getText().contains("White") && item.getText().contains("128GB"),
                    "Products does not contains expected filter options");
        }
    }

    public void checkProductTitle() {
        List<WebElement> itemTitles = driver.findElements(By.cssSelector(itemTitle));
        for (WebElement itemTitle : itemTitles) {
            System.out.println(itemTitle.getText());
            Assert.assertTrue((itemTitle.getText().toLowerCase().contains("samsung")) || (itemTitle.getText().toLowerCase().contains("apple")) || (itemTitle.getText().toLowerCase().contains("huawei")),
                    "Expected product title does not exist");
        }
    }
}


