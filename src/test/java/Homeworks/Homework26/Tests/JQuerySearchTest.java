package Homeworks.Homework26.Tests;

import Homeworks.Homework26.Pages.JQueryMainPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JQuerySearchTest {

    String searchItem = "1111";

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
                .selectRow("All")
                .provideSearch(searchItem)
                .checkSearchResults(searchItem);
    }
}
