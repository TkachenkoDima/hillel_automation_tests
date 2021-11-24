package Homeworks.Homework25.Fragments;

import Homeworks.Homework25.Pages.CitrusMainPage;
import com.codeborne.selenide.*;
import org.opentest4j.AssertionFailedError;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CitrusCartModal extends CitrusMainPage {

    private final ElementsCollection item = $$("div[class='ctrs-basket-product']");
    private final SelenideElement items = $("div.el-dialog__body");
    private final SelenideElement total = $("span[class*='ctrs-basket-footer__new-price']");
    private final SelenideElement cartWindow = $("div[class='catalog__main-content-block']");

    public CitrusCartModal verifyItemsCount(int itemsCount) {
        item.shouldHave(size(itemsCount));
        return this;
    }

    public CitrusCartModal verifyProductTitle(String expectedProduct){
        items.shouldHave(text(expectedProduct));
        return this;
    }

    public CitrusCartModal verifyProductPrice(String productPrice) {
        items.shouldHave(text(productPrice));
        return this;
    }

    public CitrusCartModal verifyCartTotal(String productPrice){
        items.shouldHave(text(productPrice));
        return this;
    }

    public CitrusCartModal verifyTotal(String firstProductPrice, String secondProductPrice) {
        int firstReplacedPrice = Integer.parseInt(firstProductPrice.replaceAll("[^0-9]", ""));
        int secondReplacedPrice = Integer.parseInt(secondProductPrice.replaceAll("[^0-9]", ""));
        int totalReplacedPrice = Integer.parseInt(total.text().replaceAll("[^0-9]", ""));
        int sumPrices = (firstReplacedPrice + secondReplacedPrice);
        boolean areEqual = (sumPrices == totalReplacedPrice);
        if (!areEqual) {
            throw new AssertionFailedError("Total price for all products in basket is incorrect");
        }
        return this;
    }
}
