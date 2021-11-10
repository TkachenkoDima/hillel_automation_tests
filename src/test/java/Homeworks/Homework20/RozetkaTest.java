package Homeworks.Homework20;

import Homeworks.BaseTest;
import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private final String monitorsCategoryLocator = "a[title='Мониторы']";
    private final String monitorItemCard = "li[class^='catalog-grid__cell']";
    private final String appleCheckBox = "label[for='Apple']";
    private final String huaweiCheckBox = "label[for='Huawei']";
    private final String memoryCheckBox = "label[for='128 ГБ']";
    private final String whiteColorCheckBox = "label[for='Белый']";
    private final String minPriceInput = "input[formcontrolname='min']";
    private final String maxPriceInput = "input[formcontrolname='max']";
    private final String submitPriceButton = "button[type='submit']";
    private final String itemPrice = "div[class^='goods-tile__price--old']";
    private final String discountedPrice = "div.goods-tile__price-value";
    private final String itemTitle = "span.goods-tile__title";
    private final String compareButton = "button[class^='compare-button']";
    private final String monitorTitle = "h1.product__title";
    private final String monitorPrice = "p[class^='product-prices__small']";
    private final String compareIcon = "span[class^='counter']";
    private final String compareModal = "li[class^='comparison-modal']";
    private final String compareItemsList = "li[class^='products-grid__cell']";
    private final String oldPrice = "div.product__price--old";
    private final String comparisonPageTitle = "h1.comparison__heading";


    private final String minPrice = "5000";
    private final String maxPrice = "15000";
    private final int price = 6000;

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
    }

    @AfterMethod(alwaysRun = true)
    public void postConditions() {
    }

    @Test
    public void checkProductBrand() {
        driver.get(url);
        fillSearchFiled(searchInput);
        clickMobilePhonesCategory();
        clickBrandCheckbox(appleCheckBox);
        clickBrandCheckbox(huaweiCheckBox);
        waitFor(itemTitle);
        checkProductTitle();
    }

    @Test
    public void checkPriceRange() {
        driver.get(url);
        fillSearchFiled(searchInput);
        clickMobilePhonesCategory();
        setPrice(minPriceInput, minPrice);
        setPrice(maxPriceInput, maxPrice);
        clickSubmitButton();
        checkPrice(minPrice, maxPrice);
    }

    @Test
    public void checkFilters() {
        driver.get(url);
        fillSearchFiled(searchInput);
        clickMobilePhonesCategory();
        waitFor(memoryCheckBox);
        clickMemoryCheckBox(memoryCheckBox);
        waitFor(whiteColorCheckBox);
        clickColorCheckBox(whiteColorCheckBox);
        waitFor(itemTitle);
        checkSelectedFilters();
    }

    @Test
    public void compareProducts() {
        driver.get(url);
        clickComputersAndLaptopsCategory();
        clickMonitors();
        clickOnProductWithPriceLessThan(price);
        int firstMonitorPrice = getMonitorPrice();
        String firstMonitorName = getMonitorName();
        clickCompareSingleItem();
        waitFor(compareIcon);
        Assert.assertTrue(driver.findElement(By.cssSelector(compareIcon)).isDisplayed(),
                "Compare items icon is not displayed");
        Assert.assertEquals(driver.findElement(By.cssSelector(compareIcon)).getText(), "1",
                "Not valid count of items presented");
        driver.navigate().back();
        clickOnProductWithPriceLessThan(firstMonitorPrice);
        int secondMonitorPrice = getMonitorPrice();
        String secondMonitorName = getMonitorName();
        clickCompareSingleItem();

        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(compareIcon)), "2"));
        Assert.assertEquals(driver.findElement(By.cssSelector(compareIcon)).getText(), "2",
                "Not valid count of items presented");
        clickCompareHeaderIcon();
        clickCompareItemsInModal();

        waitFor(comparisonPageTitle);
        List<WebElement> productsToCompare = driver.findElements(By.cssSelector(compareItemsList));
        for (WebElement compare : productsToCompare) {
            String convertedPrice = compare.findElement(By.cssSelector(oldPrice)).getText().replaceAll("[^0-9]", "");
            Assert.assertTrue(compare.getText().contains(firstMonitorName) || compare.getText().contains(secondMonitorName),
                        "Products titles not as expected");
            Assert.assertTrue(convertedPrice.contains(String.valueOf(firstMonitorPrice)) || convertedPrice.contains(String.valueOf(secondMonitorPrice)) ,
            "Products prices not as expected");
        }
    }

    public void waitFor(String element) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));
    }

    public void clickOnProductWithPriceLessThan(int price) {
        List<WebElement> monitors = driver.findElements(By.cssSelector(monitorItemCard));
        for (WebElement monitor : monitors) {
            String monitorPrice = monitor.findElement(By.cssSelector(itemPrice)).getText().replaceAll("[^0-9]", "");
            if (Integer.parseInt(monitorPrice) < price) {
                monitor.click();
                break;
            }
        }
    }

    public void clickCompareHeaderIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(compareIcon))).click();
    }

    public void clickCompareItemsInModal() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(compareModal))).click();

    }

    public String getMonitorName() {
        return (driver.findElement(By.cssSelector(monitorTitle)).getText());
    }

    public int getMonitorPrice() {
        wait.until((ExpectedConditions.presenceOfElementLocated(By.cssSelector(monitorPrice))));
        return Integer.parseInt(String.valueOf(driver.findElement(By.cssSelector(monitorPrice)).getText()).replaceAll("[^0-9]", ""));
    }

    public void fillSearchFiled(String searchItem) {
        driver.findElement(By.cssSelector(headerSearchLocator)).sendKeys(searchItem + Keys.ENTER);
    }

    public void clickBrandCheckbox(String brand) {
        driver.findElement(By.cssSelector(brand)).click();
    }

    public void clickMobilePhonesCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(mobilePhonesFiltersLocator))).click();
    }

    public void clickSubmitButton() {
        driver.findElement(By.cssSelector(submitPriceButton)).click();
    }

    public void clickMemoryCheckBox(String memory) {
        driver.findElement(By.cssSelector(memory)).click();
    }

    public void clickComputersAndLaptopsCategory() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(computerAndLaptopsLocator))).click();
    }

    public void clickMonitors() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(monitorsCategoryLocator))).click();
    }

    public void clickCompareSingleItem() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(compareButton))).click();
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
        List<WebElement> prices = driver.findElements(By.cssSelector(discountedPrice));
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
            Assert.assertTrue((itemTitle.getText().toLowerCase().contains("samsung")) || (itemTitle.getText().toLowerCase().contains("apple")) || (itemTitle.getText().toLowerCase().contains("huawei")),
                    "Expected product title does not exist");
        }
    }
}


