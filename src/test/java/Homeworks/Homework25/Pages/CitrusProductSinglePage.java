package Homeworks.Homework25.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CitrusProductSinglePage {

    private final SelenideElement butButton = $("[class*='buyButton']");

    public CitrusProductSinglePage addProductToCart() {
        butButton.click();
        return this;
    }
}
