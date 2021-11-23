package Homeworks.Homework22;

import Homeworks.UIBaseTest;
import Pages.RozetkaPages.RozetkaMainPage;
import Pages.RozetkaPages.RozetkaProductSinglePage;
import Pages.RozetkaPages.RozetkaProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaPageObjectTestUI extends UIBaseTest {
    private final String url = "https://rozetka.com.ua/";
    private final String minPrice = "5000";
    private final String maxPrice = "15000";
    private final int price = 6000;

    RozetkaMainPage rozetkaMainPage;
    RozetkaProductsPage rozetkaProductsPage;
    RozetkaProductSinglePage rozetkaProductSinglePage;

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        driver.get(url);
        rozetkaMainPage = new RozetkaMainPage(driver);
        rozetkaProductsPage = new RozetkaProductsPage(driver);
        rozetkaProductSinglePage = new RozetkaProductSinglePage(driver);
    }

    @Test
    public void checkProductBrand() throws InterruptedException {
        rozetkaMainPage.performSearch("Samsung");
        rozetkaProductsPage.clickMobilePhonesCategory();
        Thread.sleep(1000);
        rozetkaProductsPage.clickAppleBrandCheckbox();
        Thread.sleep(1000);
        rozetkaProductsPage.clickHuaweiBrandCheckbox();
        Thread.sleep(1000);
        checkProductTitle();
    }

    @Test
    public void checkPriceRange() throws InterruptedException {
        rozetkaMainPage.performSearch("Samsung");
        rozetkaProductsPage.clickMobilePhonesCategory()
            .setPriceRange(minPrice, maxPrice);
        Thread.sleep(1000);
        rozetkaProductsPage.clickSubmitPriceRangeButton();
        Thread.sleep(1000);
        checkPrice(minPrice, maxPrice);
    }

    @Test
    public void checkFilters() {
        rozetkaMainPage.performSearch("Samsung");
        rozetkaProductsPage.clickMobilePhonesCategory()
                .click128GBMemoryCheckBox()
                .clickWhiteColorFilterCheckBox();
        checkSelectedFilters();
    }

    @Test
    public void compareProducts() throws InterruptedException {
        rozetkaMainPage.clickComputerAndLaptopsCategory()
        .clickMonitors();
        rozetkaProductsPage.clickOnProductWithPriceLessThan(price);
        int firstMonitorPrice = rozetkaProductSinglePage.getMonitorPrice();
        String firstMonitorName = rozetkaProductSinglePage.getMonitorName();
        rozetkaProductSinglePage.clickCompareSingleItem();
        Assert.assertFalse(rozetkaMainPage.isCartEmpty(),
                "Compare items icon is not displayed");
        driver.navigate().back();
        rozetkaProductsPage.clickOnProductWithPriceLessThan(firstMonitorPrice);
        int secondMonitorPrice = rozetkaProductSinglePage.getMonitorPrice();
        String secondMonitorName = rozetkaProductSinglePage.getMonitorName();
        rozetkaProductSinglePage.clickCompareSingleItem();
        Thread.sleep(1000);
        Assert.assertEquals(rozetkaMainPage.getCartLabelText(), "2",
                "Not valid count of items presented");
        rozetkaMainPage.clickCompareHeaderIcon()
        .clickCompareItemsInModal();
        Thread.sleep(1000);
        checkMonitorsTitles(firstMonitorName, secondMonitorName);
        checkMonitorsPrices(firstMonitorPrice, secondMonitorPrice);
    }

    private void checkMonitorsTitles(String firstMonitorName, String secondMonitorName) {
        List<WebElement> productsToCompare = rozetkaMainPage.getCompareItemList();
        for (WebElement compare : productsToCompare) {
            Assert.assertTrue(compare.getText().contains(firstMonitorName) || compare.getText().contains(secondMonitorName),
                    "Products titles not as expected");
        }
    }

    public void checkMonitorsPrices(int firstMonitorPrice, int secondMonitorPrice) {
        List<WebElement> productsToCompare = rozetkaProductSinglePage.getOldMonitorPrice();
        for (WebElement compare : productsToCompare) {
            String convertedPrice = compare.getText().replaceAll("[^0-9]", "");
            Assert.assertTrue(convertedPrice.contains(String.valueOf(firstMonitorPrice)) || convertedPrice.contains(String.valueOf(secondMonitorPrice)),
                    "Product prices not as expected");
        }
    }

    public void checkProductTitle() {
        List<WebElement> itemTitles = rozetkaProductsPage.getProductTitle();
        for (WebElement itemTitle : itemTitles) {
            Assert.assertTrue((itemTitle.getText().toLowerCase().contains("samsung")) || (itemTitle.getText().toLowerCase().contains("apple")) || (itemTitle.getText().toLowerCase().contains("huawei")),
                    "Expected product title does not exist");
        }
    }

    public void checkSelectedFilters() {
        List<WebElement> items = rozetkaProductsPage.getProductTitle();
        for (WebElement item : items) {
            Assert.assertTrue(item.getText().contains("White") && item.getText().contains("128GB"),
                    "Products does not contains expected filter options");
        }
    }

    public void checkPrice(String minPrice, String maxPrice) {
        List<WebElement> prices = rozetkaProductsPage.getDiscountedPrice();
        for (WebElement price : prices) {
            String replacedPrices = price.getText().replace(" ", "");
                Assert.assertTrue((Integer.parseInt(replacedPrices) >= Integer.parseInt(minPrice)) && (Integer.parseInt(replacedPrices) <= Integer.parseInt(maxPrice)),
                        "Price limit is not as expected");
        }
    }
}


