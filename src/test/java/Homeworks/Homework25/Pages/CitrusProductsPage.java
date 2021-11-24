package Homeworks.Homework25.Pages;

import Homeworks.Homework25.Fragments.CitrusAddedProductModal;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.exec.OS;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;

public class CitrusProductsPage extends CitrusMainPage{

    CitrusAddedProductModal addedProductModal = new CitrusAddedProductModal();

    private final ElementsCollection productCard = $$("div.catalog-item");
    private final ElementsCollection laptops = $$("div[class*='border-box pr productCardCategory']");
    private final ElementsCollection asusLaptops = $$("div[class='catalog-item product-card__']");
    private final ElementsCollection productCards = $$("div[class*='productCardCategory']");
    private final SelenideElement buyButton = $("i[class*='citrus-cart el-tooltip item'");
    private final SelenideElement itemTitle = $("div.title-itm");
    private final ElementsCollection priceInput = $$("input[type='tel'");
    private final ElementsCollection productsPrices = $$("div[data-price]");
    private final SelenideElement applyPriceRangeButton = $("button[type='submit']");
    private final ElementsCollection productsTitles = $$("a[class*='link line-clamp-2 break-word']");
    private final ElementsCollection filterCheckbox = $$("div[class^='filterItemContainer']");
    private final SelenideElement laptopTitle = $("a[class*='line-clamp-2 break-word']");
    private final SelenideElement laptopPrice = $("div[class*='medium']");
    private final SelenideElement asusLaptopComparisonIcon = $("i[class^='icon-comparison2']");

    public CitrusProductsPage isProductPresentAtSearchResults(String expectedProduct) {
        product(expectedProduct).should(exist);
        return this;
    }

    public CitrusProductsPage clickBuyButton(String expectedProduct) {
        product(expectedProduct).$("i[class*='citrus-cart el-tooltip item'").click();
        return this;
    }

    public CitrusProductsPage clickMemoryFilterCheckBox(String memory) {
        filterCheckbox.find(text(memory)).shouldBe(visible).click();
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

    public String getLaptopTitle(int elementNumber) {
        return laptops.get(elementNumber).$("a[class*='line-clamp-2 break-word']").text();
    }

    public String getLaptopPrice(int elementNumber) {
        return laptops.get(elementNumber).$("div[class*='medium']").text().replace("₴", "");
    }

    public String getAsusLaptopTitle(int elementNumber) {
        return asusLaptops.get(elementNumber).$("div.title-itm").text();
    }

    public String getAsusLaptopPrice(int elementNumber) {
        return asusLaptops.get(elementNumber).$("span.price-number").text().replace("₴", "");
    }

    public CitrusProductsPage addProductToCartByNum(int elementNumber) {
        productCard.get(elementNumber).$("i[class*='citrus-cart el-tooltip item'").click();
        addedProductModal.closeAddedProductModal();
        return this;
    }

    public CitrusProductsPage addProductToComparison(int elementNumber) {
        productCard.get(elementNumber).$("i[class*='icon-comparison2 el-tooltip item'").click();
        return this;
    }

    public CitrusProductsPage addLaptopToComparisonByNum(int elementNumber) {
        laptops.get(elementNumber).$("button[class*='uppercase medium btn']").click();
        return this;
    }

    public CitrusProductsPage addAsusLaptopToComparisonByNum(int elementNumber) {
        asusLaptops.get(elementNumber).$("i[class^='icon-comparison2']").click();
        return this;
    }

    public CitrusProductsPage setMinPrice(int minPrice) {
        priceInput.get(0).val(String.valueOf(minPrice));
        return this;
    }

    public CitrusProductsPage setMaxPrice(int maxPrice) {
        clearInputWithKeys(priceInput.get(1));
        priceInput.get(1).val(String.valueOf(maxPrice));
        return this;
    }

    public CitrusProductsPage verifyAllFilteredProductsByBrand(String brand) {
        for (WebElement product : productsTitles) {
            if (!product.getText().contains(brand)) {
                throw new AssertionFailedError("Product title are not as expected");
            }
        }
        return this;
    }

    public CitrusProductsPage clearInputWithKeys(SelenideElement element){
        if (OS.isFamilyMac()) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            element.sendKeys(Keys.DELETE);
        } else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    public CitrusProductsPage verifyAllFilteredProductsByPrice(int minPrice, int maxPrice) {
        for (WebElement price : productsPrices) {
            int convertedPrice = Integer.parseInt(price.getText().replaceAll("[^0-9]", ""));
            if ((convertedPrice < minPrice) || (convertedPrice > maxPrice)) {
                throw new AssertionFailedError("Product price are not as expected");
            }
        }
        return this;
    }

    public CitrusProductsPage clickApplyPriceRangeButton() {
        applyPriceRangeButton.click();
        return this;
    }

    public CitrusProductsPage verifyAllFilteredProductByMemoryFilter(String memory32GB, String memory64GB) {
        for (WebElement product : productsTitles) {
            if (!product.getText().contains(memory32GB) && !product.getText().contains(memory64GB)){
                throw new AssertionFailedError("Product filters are not as expected");
            }
        }
        return this;
    }
}
