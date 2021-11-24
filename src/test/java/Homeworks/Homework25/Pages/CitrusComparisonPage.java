package Homeworks.Homework25.Pages;

import Homeworks.Homework25.Fragments.CitrusCartModal;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CitrusComparisonPage extends CitrusMainPage{
    private final ElementsCollection comparisonItems = $$("div.relative");
    private final ElementsCollection comparisonPrices = $$("span.price-number");
    private final SelenideElement comparison = $("div[class*='big-container']");
    private final SelenideElement comparisonPage = $("h1[class='compare__title']");

    public CitrusComparisonPage itemsCountInComparison(int items) {
        comparisonItems.shouldHave(CollectionCondition.size(items));
        return this;
    }

    public CitrusComparisonPage comparisonPageShouldBeVisible() {
        comparisonPage.shouldBe(visible);
        return this;
    }

    public CitrusComparisonPage verifyProductTitle(String expectedProduct){
        for (WebElement title : comparisonItems) {
            if (!title.getText().contains(expectedProduct)){
                throw new AssertionFailedError("Product titles are not as expected");
            }
        }
        return this;
    }

    public CitrusComparisonPage verifyLaptopProductTitle(String expectedProduct){
        comparison.shouldHave(text(expectedProduct));
        return this;
    }

    public CitrusComparisonPage verifyLaptopProductPrice(String expectedPrice){
        comparison.shouldHave(text(expectedPrice));
        return this;
    }

    public CitrusComparisonPage verifyProductPrice(String price) {
        for (WebElement prices : comparisonPrices) {
            if(!prices.getText().contains(price)){
                throw new AssertionFailedError("Products prices are not as expected");
            }
        }
        return this;
    }
}
