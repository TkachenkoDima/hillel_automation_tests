package Homeworks.Homework26.Tests;

import Homeworks.Homework26.Pages.RozetkaPage;
import com.codeborne.selenide.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StreamApiRozetkaTest {

    String searchItem = "lenovo";
    String partialText = "IdeaPad";
    int price = 8000;

    RozetkaPage rozetkaPage;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://rozetka.com.ua";
    }

    @Test
    public void test() {
        rozetkaPage = new RozetkaPage();
        rozetkaPage.open()
                .performSearch(searchItem)
                .getAllLenovoPrices()
                .getAllLenovoTitles()
                .getAllItemsWithPriceAbove(price)
                .qtyProductsWithPriceAbove(price)
                .findItemWithMaxPrice()
                .findProductsPricesByPartialTitleText(partialText);
    }
}
