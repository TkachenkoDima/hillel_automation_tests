package Homeworks.Homework25.Tests;

import Homeworks.Homework25.Fragments.CitrusAddedProductModal;
import Homeworks.Homework25.Fragments.CitrusCartModal;
import Homeworks.Homework25.Pages.CitrusProductSinglePage;
import Homeworks.Homework25.Pages.CitrusProductsPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

import Homeworks.Homework25.Fragments.CitrusHeaderFragment;
import Homeworks.Homework25.Pages.CitrusMainPage;

public class AddTwoProductsToBasketFromComparison_Test4 {
    String searchItem = "Apple iPhone";

    CitrusMainPage mainPage;
    CitrusCartModal cartModal;
    CitrusProductsPage productsPage;
    CitrusHeaderFragment headerFragment;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
    }

    @Test
    public void test() {
        mainPage = new CitrusMainPage();
        cartModal = new CitrusCartModal();
        productsPage = new CitrusProductsPage();
        headerFragment = new CitrusHeaderFragment();

        mainPage.open().closeAdPopup();
        headerFragment.performSearch(searchItem);
        String firstProductTitle = productsPage.getProductTitle(0);
        String secondProductTitle = productsPage.getProductTitle(1);
        String firstProductPrice = productsPage.getProductPriceByNum(0);
        String secondProductPrice = productsPage.getProductPriceByNum(1);
        productsPage.addProductToComparison(0);
        headerFragment.comparisonCounterHaveItems("1");
        productsPage.addProductToComparison(1);
        headerFragment.comparisonCounterHaveItems("2");
        headerFragment.clickComparison();
        productsPage.addProductToCartByNum(0);
        productsPage.addProductToCartByNum(3);
        sleep(2000);
        headerFragment.clickCart();
        cartModal.verifyItemsCount(2)
                .verifyProductPrice(firstProductPrice)
                .verifyProductPrice(secondProductPrice)
                .verifyProductTitle(firstProductTitle)
                .verifyProductTitle(secondProductTitle)
                .verifyTotal(firstProductPrice, secondProductPrice);
    }
}
