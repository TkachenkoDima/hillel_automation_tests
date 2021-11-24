package Homeworks.Homework25.Tests;

import Homeworks.Homework25.Fragments.CitrusAddedProductModal;
import Homeworks.Homework25.Fragments.CitrusCartModal;
import Homeworks.Homework25.Pages.CitrusProductsPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

import Homeworks.Homework25.Fragments.CitrusHeaderFragment;
import Homeworks.Homework25.Pages.CitrusMainPage;

public class AddTwoProductsToBasket_Test3 {

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
        productsPage.addProductToCartByNum(0);
        productsPage.addProductToCartByNum(1);
        sleep(2000);
        headerFragment.clickCart();
        cartModal.verifyItemsCount(2)
//could be changed for new method verifyProductTitles with check expected titles within $$ElementCollection, but I'm too lazy
                .verifyProductTitle(firstProductTitle)
                .verifyProductTitle(secondProductTitle)
                .verifyProductPrice(firstProductPrice)
                .verifyProductPrice(secondProductPrice)
                .verifyTotal(firstProductPrice, secondProductPrice);
    }
}
