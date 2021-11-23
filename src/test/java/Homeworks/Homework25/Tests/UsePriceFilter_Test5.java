package Homeworks.Homework25.Tests;

import Homeworks.Homework25.Fragments.CitrusAddedProductModal;
import Homeworks.Homework25.Fragments.CitrusCartModal;
import Homeworks.Homework25.Fragments.CitrusHeaderFragment;
import Homeworks.Homework25.Pages.CitrusMainPage;
import Homeworks.Homework25.Pages.CitrusProductSinglePage;
import Homeworks.Homework25.Pages.CitrusProductsPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class UsePriceFilter_Test5 {

    String category = "Смартфоны";
    String brand = "Samsung";
    int minPrice = 20000;
    int maxPrice = 40000;


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
        headerFragment = new CitrusHeaderFragment();
        productSinglePage = new CitrusProductSinglePage();
        addedProductModal = new CitrusAddedProductModal();

        mainPage.open().closeAdPopup();
        mainPage.hoverOverCategory(category)
                .chooseByProducer(brand);
        productsPage.setMinPrice(minPrice);
        productsPage.setMaxPrice(maxPrice)
                .clickApplyPriceRangeButton()
                .verifyAllFilteredProductsByBrand(brand);
//        productsPage.verifyAllFilteredProductsByPrice(minPrice, maxPrice);


    }
}
