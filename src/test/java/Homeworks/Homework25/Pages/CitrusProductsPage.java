package Homeworks.Homework25.Pages;

import Homeworks.Homework25.Fragments.CitrusAddedProductModal;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CitrusProductsPage {

    CitrusAddedProductModal addedProductModal = new CitrusAddedProductModal();

    private final ElementsCollection productCard = $$("div.catalog-item");
    private final ElementsCollection productCards = $$("div[class*='productCardCategory']");
    private final SelenideElement buyButton = $("i[class*='citrus-cart el-tooltip item'");
    private final SelenideElement itemTitle = $("div.title-itm");
    private final ElementsCollection priceInput = $$("input[type='tel'");
    private final ElementsCollection productsPrices = $$("div[data-price]");
    private final SelenideElement applyPriceRangeButton = $("button[type='submit']");
    private final ElementsCollection productsTitles = $$("a[class*='link line-clamp-2 break-word']");

    public CitrusProductsPage isProductPresentAtSearchResults(String expectedProduct) {
        product(expectedProduct).should(exist);
        return this;
    }

    public CitrusProductsPage clickBuyButton(String expectedProduct) {
        product(expectedProduct).$("i[class*='citrus-cart el-tooltip item'").click();
        return this;
    }

    private SelenideElement product(String expectedProduct) {
        return productCard.find(text(expectedProduct));
    }

    public String getProductPrice(String expectedProduct) {
        return product(expectedProduct).$("span[class='price-number']").text();
    }

    public CitrusProductSinglePage clickExpectedProduct(String searchItem) {
        product(searchItem).click();
        return new CitrusProductSinglePage();
    }

    public String getProductTitle(int elementNumber) {
       return productCard.get(elementNumber).$("div.title-itm").text();
    }

    public String getProductPriceByNum(int elementNumber) {
        return productCard.get(elementNumber).$("span.price-number").text();
    }

    public CitrusProductsPage addProductToCartByNum(int elementNumber) {
        productCard.get(elementNumber).$("i[class*='citrus-cart el-tooltip item'").click();
        addedProductModal.closeAddedProductModal();
        return this;
    }

    public CitrusProductsPage adProductToComparison(int elementNumber) {
        productCard.get(elementNumber).$("i[class*='icon-comparison2 el-tooltip item'").click();
        return this;
    }

    public CitrusProductsPage setMinPrice (int minPrice) {
        priceInput.get(0).val(String.valueOf(minPrice));
        return this;
    }

    public CitrusProductsPage setMaxPrice (int maxPrice) {
        priceInput.get(1).clear();
        priceInput.get(1).val(String.valueOf(maxPrice));
        return this;
    }

    public CitrusProductsPage verifyAllFilteredProductsByBrand(String brand) {
        for (WebElement titles : productsTitles) {
            System.out.println(titles.getText());
            if (!titles.getText().contains(brand)){
                throw new AssertionFailedError("Product title are not as expected");
            }
        }
        return this;
    }

    public CitrusProductsPage verifyAllFilteredProductsByPrice(int minPrice, int maxPrice) {
        for (WebElement price : productsPrices) {
            System.out.println(price.getText());
        }
        return this;
    }

    public CitrusProductsPage clickApplyPriceRangeButton() {
        applyPriceRangeButton.click();
        return this;
    }
}
