package Homeworks.Homework23;

import Homeworks.BaseTest;
import Pages.SportcheckPages.SportCheckPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SportCheckAddToCartTest extends BaseTest {
    private final String url = "https://www.sportchek.ca/categories/women/tops/hoodies/product/champion-womens-sportswear-powerblend-applique-hoodie-color-333261000_06-333261000.html#333261000%5Bcolor%5D=333261000_06";

    SportCheckPage sportCheckPage;

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
    }

    @Test
    public void addItemToCart() {
        sportCheckPage = new SportCheckPage(driver);
        driver.get(url);
        sportCheckPage.selectColor();
        sportCheckPage.selectSize();
//        Assert.assertTrue(sportCheckPage.isInStockLabelDisplayed(),
//                "Label not displayed!");
        sportCheckPage.clickQuantity(2);
        sportCheckPage.clickAddToCartButton();
        Assert.assertTrue(sportCheckPage.isCartModalWindowDisplayed(),
                "Modal window is not displayed!");
        sportCheckPage.moveToCartIcon();
        Assert.assertEquals(sportCheckPage.getQuantity(), sportCheckPage.getCartQuantity(),
                "Quantity not equals!");
        Assert.assertEquals(sportCheckPage.getProductTitle(), sportCheckPage.getCartItemTitle(),
                "Product title not as expected!");
    }
}
