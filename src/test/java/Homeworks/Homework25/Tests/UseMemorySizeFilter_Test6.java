package Homeworks.Homework25.Tests;

import Homeworks.Homework25.Pages.CitrusMainPage;
import Homeworks.Homework25.Pages.CitrusProductsPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UseMemorySizeFilter_Test6 {

    String category = "Смартфоны";
    String brand = "Xiaomi";
    String memory32GB = "32";
    String memory64GB = "64";

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
        productsPage.clickMemoryFilterCheckBox(memory32GB)
                .clickMemoryFilterCheckBox(memory64GB)
                .pageIsLoaded();
        productsPage.verifyAllFilteredProductsByBrand(brand)
                .verifyAllFilteredProductByMemoryFilter(memory32GB, memory64GB);
    }
}
