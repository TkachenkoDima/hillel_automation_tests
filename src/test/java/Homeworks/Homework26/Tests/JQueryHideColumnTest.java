package Homeworks.Homework26.Tests;

import Homeworks.Homework26.Pages.JQueryMainPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JQueryHideColumnTest {

    String row = "All";
    String column = "Sender";

    JQueryMainPage mainPage;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "http://www.jquery-bootgrid.com";
        Configuration.browser = "safari";
    }

    @Test
    public void test() {
        mainPage = new JQueryMainPage();
        mainPage.open()
                .clickStartExample()
                .clickDropdownRowsIcon()
                .selectRow(row)
                .checkTableColumnIsVisible(column)
                .clickDropdownTabsIcon()
                .selectColumnsToHide(column)
                .checkTableColumnIsNotVisible(column);
    }
}
