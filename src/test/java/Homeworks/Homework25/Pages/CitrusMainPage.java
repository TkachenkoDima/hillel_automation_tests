package Homeworks.Homework25.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CitrusMainPage {

    private final SelenideElement adPopupCloseButton = $("[class*=closeAdvertising]");
    private final SelenideElement brandIcon = $(" ");
    private final SelenideElement page = $("div.main-container");

    public CitrusMainPage open() {
        Selenide.open("/");
        return this;
    }

    public CitrusMainPage closeAdPopup() {
        if(adPopupCloseButton.isDisplayed()) {
            adPopupCloseButton.click();
        }
        return this;
    }

    public CitrusMainPage clickBrand(String brand) {
        $("img[alt='samsung']");
        return this;
    }


    public CitrusMainPage hoverOverCategory(String category) {
        $(byText(category)).hover();
        return this;
    }

    public CitrusMainPage chooseByProducer(String brand) {
        $(byText(brand)).shouldBe(visible).click();
        return this;
    }

    public CitrusMainPage chooseByCategory(String category) {
        $(byText("Смартфоны")).click();
        return this;
    }

    public CitrusMainPage pageIsLoaded() {
        page.shouldBe(visible);
        return this;
    }
}
