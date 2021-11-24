package Homeworks.Homework25.Tests;

import Homeworks.Homework25.Fragments.CitrusHeaderFragment;
import Homeworks.Homework25.Pages.CitrusComparisonPage;
import Homeworks.Homework25.Pages.CitrusMainPage;
import Homeworks.Homework25.Pages.CitrusProductsPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class CompareTwoPlusOneProducts_Test7 {
    String category = "Ноутбуки, планшеты, МФУ";
    String brand = "Acer";
    String searchItem = "Asus notebook";

    CitrusMainPage mainPage;
    CitrusProductsPage productsPage;
    CitrusHeaderFragment headerFragment;
    CitrusComparisonPage comparisonPage;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
    }

    @Test
    public void test() {
        mainPage = new CitrusMainPage();
        productsPage = new CitrusProductsPage();
        headerFragment = new CitrusHeaderFragment();
        comparisonPage = new CitrusComparisonPage();

        mainPage.open().closeAdPopup()
                .hoverOverCategory(category)
                .chooseByProducer(brand);
        sleep(2000);
        String firstProductTitle = productsPage.getLaptopTitle(0);
        String secondProductTitle = productsPage.getLaptopTitle(1);
        String firstProductPrice = productsPage.getLaptopPrice(0);
        String secondProductPrice = productsPage.getLaptopPrice(1);
        productsPage.addLaptopToComparisonByNum(0);
        headerFragment.comparisonLaptopsCounter("1");
        productsPage.addLaptopToComparisonByNum(1);
        headerFragment.comparisonLaptopsCounter("2");
        headerFragment.clickLaptopsComparison();
        comparisonPage.itemsCountInComparison(2)
                .verifyLaptopProductTitle(firstProductTitle)
                .verifyLaptopProductTitle(secondProductTitle)
                .verifyLaptopProductPrice(firstProductPrice)
                .verifyLaptopProductPrice(secondProductPrice);
        headerFragment.performSearchFromComparisonPage(searchItem);
        String thirdProductTitle = productsPage.getAsusLaptopTitle(0);
        String thirdProductPrice = productsPage.getAsusLaptopPrice(0);
        productsPage.addAsusLaptopToComparisonByNum(0);
        sleep(1000);
        headerFragment.clickComparison();
        comparisonPage.comparisonPageShouldBeVisible()
                .itemsCountInComparison(3)
                .verifyLaptopProductTitle(firstProductTitle)
                .verifyLaptopProductTitle(secondProductTitle)
                .verifyLaptopProductTitle(thirdProductTitle)
                .verifyLaptopProductPrice(firstProductPrice)
                .verifyLaptopProductPrice(secondProductPrice)
                .verifyLaptopProductPrice(thirdProductPrice);
    }
}
