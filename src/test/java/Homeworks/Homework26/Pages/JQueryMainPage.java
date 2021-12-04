package Homeworks.Homework26.Pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class JQueryMainPage {

    private final SelenideElement startExampleButton = $("button#init-selection");
    private final SelenideElement dropdownToggle = $("button[class*='dropdown-toggle']");
    private final ElementsCollection dropdownItems = $$("a[class*='dropdown-item-button']");
    private final SelenideElement selectTabsIcon = $("span[class*='fa-th-list']");
    private final ElementsCollection columnCheckbox = $$("label[class='dropdown-item']");
    private final SelenideElement table = $("table#grid-selection");
    private final SelenideElement searchField = $("input[class^='search-field']");
    private final SelenideElement searchRow = $("tr[data-row-id='']");
    private final ElementsCollection rows = $$("tr[data-row-id]");
    private final SelenideElement column = $("th[data-column-id='received']");
    private final SelenideElement receivedColumnButton = $("span[class='icon fa fa-sort-desc']");
    private final ElementsCollection dates = $$x("//tr/td[4]");

    public JQueryMainPage open() {
        Selenide.open("/examples#selection");
        return this;
    }

    public JQueryMainPage clickStartExample() {
        startExampleButton.click();
        return this;
    }

    public JQueryMainPage clickDropdownRowsIcon() {
        dropdownToggle.click();
        return this;
    }

    public JQueryMainPage selectRow(String condition) {
        dropdownItems.find(text(condition)).click();
        return this;
    }

    public JQueryMainPage clickDropdownTabsIcon() {
        selectTabsIcon.click();
        return this;
    }

    public JQueryMainPage selectColumnsToHide(String state) {
        columnCheckbox.find(text(state)).click();
        return this;
    }

    public JQueryMainPage checkTableColumnIsVisible(String column) {
        table.shouldHave(text(column)).shouldBe(visible);
        return this;
    }

    public JQueryMainPage checkTableColumnIsNotVisible(String column) {
        table.shouldNotHave(text(column));
        return this;
    }

    public JQueryMainPage provideSearch(String searchItem) {
        searchField.val(searchItem).sendKeys(Keys.ENTER);
        return this;
    }

    public JQueryMainPage checkSearchResults(String searchItem) {
        $("tr[data-row-id='" + searchItem + "']").shouldBe(visible);
        rows.shouldHave(size(11));
        return this;
    }

    public JQueryMainPage clickReceivedColumn() {
        receivedColumnButton.click();
        return this;
    }

    public JQueryMainPage checkSortIsAsc() {
        sleep(1000);
        ArrayList<String> obtainedList = new ArrayList<>();
        for (SelenideElement e : dates) {
            obtainedList.add(e.getText());
        }

        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : obtainedList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        Assert.assertEquals(obtainedList, sortedList);
        return this;
    }
}