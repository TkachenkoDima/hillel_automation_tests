package Homeworks.Homework26.Tests;

import Homeworks.Homework26.Pages.JQueryMainPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JQuerySortTest {

    JQueryMainPage mainPage;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://www.jquery-bootgrid.com";
    }

    @Test
    public void test() {
        mainPage = new JQueryMainPage();
        mainPage.open()
                .clickStartExample()
                .clickDropdownRowsIcon()
                .selectRow("All")
                .clickReceivedColumn()
                .checkSortIsAsc();
    }
}