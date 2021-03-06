package Homeworks.Homework25.Tests;

import Homeworks.Homework25.Pages.CitrusMainPage;
import Homeworks.Homework25.Pages.CitrusProductsPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class UsePriceFilter_Test5 {

    String category = "Смартфоны";
    String brand = "Samsung";
    int minPrice = 30000;
    int maxPrice = 40000;

    CitrusMainPage mainPage;
    CitrusProductsPage productsPage;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
    }

    @Test
    public void test() {
        mainPage = new CitrusMainPage();
        productsPage = new CitrusProductsPage();

        mainPage.open().closeAdPopup()
                .hoverOverCategory(category)
                .chooseByProducer(brand);
        productsPage.setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .clickApplyPriceRangeButton();
        sleep(1000);
        productsPage.verifyAllFilteredProductsByBrand(brand)
                .verifyAllFilteredProductsByPrice(minPrice, maxPrice);
    }
}
