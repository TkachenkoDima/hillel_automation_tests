package Homeworks.Homework25.Fragments;

import Homeworks.Homework25.Pages.CitrusMainPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CitrusHeaderFragment extends CitrusMainPage {
    private final SelenideElement searchField = $("input[class^='search-input'");
    private final SelenideElement cartIcon = $("i[class='icon-new-citrus-cart']");
    private final SelenideElement comparisonIcon = $("i[class='icon-comparison2']");
    private final SelenideElement comparisonCounter = $("span.counter");
    private final SelenideElement getComparisonLaptopsIcon = $("div[class*='badge-0-2-78']");
    private final SelenideElement comparisonIconLaptops = $("a[class*='compare']");
    private final SelenideElement searchFieldFromComparisonPage = $("input#search-input");
    private final SelenideElement cartModal = $("div[class='el-dialog__header']");

    private final SelenideElement cartCounter = $("span.counter").ancestor("div");

    public CitrusHeaderFragment performSearch(String searchItem) {
        searchField.val(searchItem).sendKeys(Keys.ENTER);
        return this;
    }

    public CitrusHeaderFragment performSearchFromComparisonPage(String searchItem) {
        searchFieldFromComparisonPage.val(searchItem).sendKeys(Keys.ENTER);
        return this;
    }

    public CitrusHeaderFragment clickCart() {
        cartIcon.click();
        return this;
    }

    public CitrusHeaderFragment clickComparison() {
        comparisonIcon.click();
        return this;
    }

    public CitrusHeaderFragment clickLaptopsComparison() {
        comparisonIconLaptops.click();
        return this;
    }

    public CitrusHeaderFragment comparisonCounterHaveItems(String itemsCount) {
        comparisonCounter.shouldHave(text(itemsCount));
        return this;
    }

    public CitrusHeaderFragment comparisonLaptopsCounter(String itemsCount) {
        comparisonIconLaptops.shouldHave(text(itemsCount));
        return this;
    }

    public CitrusHeaderFragment cartCounterHaveItems(String itemsCount) {
        cartCounter.shouldHave(text(itemsCount));
        return this;
    }
}
