package Homeworks.Homework25.Fragments;

import Homeworks.Homework25.Pages.CitrusMainPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CitrusHeaderFragment extends CitrusMainPage {
    private final SelenideElement searchField = $("input[class^='search-input'");
    private final SelenideElement cartIcon = $("div[class*='basket']");
    private final SelenideElement comparisonIcon = $("i[class='icon-comparison2']");
    private final SelenideElement comparisonCounter = $("span.counter");
    private final SelenideElement cartCounter = $("span.counter").ancestor("div");

    public CitrusHeaderFragment performSearch(String searchItem) {
        searchField.val(searchItem).sendKeys(Keys.ENTER);
        return this;
    }

    public CitrusHeaderFragment clickCart() {
        cartIcon.click();
        cartIcon.should(appear);
        return this;
    }

    public CitrusHeaderFragment clickComparison() {
        comparisonIcon.click();
        return this;
    }

    public CitrusHeaderFragment comparisonCounterHaveItems(String itemsCount) {
        comparisonCounter.shouldHave(text(itemsCount));
        return this;
    }

    public CitrusHeaderFragment cartCounterHaveItems(String itemsCount) {
        cartCounter.shouldHave(text(itemsCount));
        return this;
    }
}
