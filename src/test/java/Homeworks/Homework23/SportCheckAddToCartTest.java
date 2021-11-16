package Homeworks.Homework23;

import Homeworks.BaseTest;
import Pages.SportcheckPages.SportCheckPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SportCheckAddToCartTest extends BaseTest {
    private final String url = "https://www.sportchek.ca/categories/women/tops/hoodies/product/champion-womens-sportswear-powerblend-applique-hoodie-color-333261000_06-333261000.html#333261000%5Bcolor%5D=333261000_06";

    SportCheckPage sportCheckPage;

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        driver.get(url);
    }

    @Test
    public void addItemToCart() {
        sportCheckPage = new SportCheckPage(driver);
        driver.get(url);

    }

}
