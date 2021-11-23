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


public class AddToBasketUsingSearch_Test2 {
    String searchItem = "Apple iPhone 12";
    String expectedProduct = "Apple iPhone 12 128GB Purple";

    CitrusMainPage mainPage;
    CitrusCartModal cartModal;
    CitrusProductsPage productsPage;
    CitrusHeaderFragment headerFragment;
    CitrusProductSinglePage productSinglePage;
    CitrusAddedProductModal addedProductModal;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
    }

    @Test
    public void addItemToCart() {
        mainPage = new CitrusMainPage();
        cartModal = new CitrusCartModal();
        productsPage = new CitrusProductsPage();
        headerFragment  = new CitrusHeaderFragment();
        productSinglePage = new CitrusProductSinglePage();
        addedProductModal = new CitrusAddedProductModal();

        mainPage.open().closeAdPopup();
        headerFragment.performSearch(searchItem);
        sleep(1000);
        productsPage.isProductPresentAtSearchResults(expectedProduct);
        String productPrice = productsPage.getProductPrice(expectedProduct);
        productsPage.clickBuyButton(expectedProduct);
        addedProductModal.closeAddedProductModal();
        sleep(2000);
        headerFragment.clickCart();
        sleep(1000);
        cartModal.verifyItemsCount(1)
                .verifyProductTitle(expectedProduct)
                .verifyProductPrice(productPrice)
                .verifyCartTotal(productPrice);
    }
}
