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
    String expectedProduct = "Apple iPhone 12 Pro Max 256GB Pacific Blue";

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
    public void test() {
        mainPage = new CitrusMainPage();
        cartModal = new CitrusCartModal();
        productsPage = new CitrusProductsPage();
        headerFragment  = new CitrusHeaderFragment();
        productSinglePage = new CitrusProductSinglePage();
        addedProductModal = new CitrusAddedProductModal();

        mainPage.open().closeAdPopup();
        headerFragment.performSearch(searchItem);
        productsPage.isProductPresentAtSearchResults(expectedProduct);
        String productPrice = productsPage.getProductPrice(expectedProduct);
        productsPage.clickBuyButton(expectedProduct);
        addedProductModal.closeAddedProductModal();
        sleep(3000);
        headerFragment.clickCart();
        cartModal.verifyItemsCount(1)
                .verifyProductTitle(expectedProduct)
                .verifyProductPrice(productPrice)
                .verifyCartTotal(productPrice);
    }
}
