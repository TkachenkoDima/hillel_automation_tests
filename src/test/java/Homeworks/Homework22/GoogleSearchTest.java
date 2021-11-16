package Homeworks.Homework22;

import Homeworks.BaseTest;
import Pages.GooglePages.GoogleMainPage;
import Pages.GooglePages.GoogleSearchResultPage;
import Pages.GooglePages.SearchResultsException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {
    private final String url = "https://www.google.com/";
    private final String searchInput = "iphone kyiv buy";
    private final String expectedWebsite = "allo.ua";

    GoogleMainPage googleMainPage;
    GoogleSearchResultPage googleSearchResultPage;

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        driver.get(url);
    }

    @Test
    public void iPhoneSearchOnFirstFiveGooglePages() throws SearchResultsException {
        googleMainPage = new GoogleMainPage(driver);
        googleSearchResultPage = new GoogleSearchResultPage(driver);

        googleMainPage.performSearch(searchInput);
        Assert.assertEquals(googleSearchResultPage.getSearchFieldText(), searchInput);
        googleSearchResultPage.checkResults(expectedWebsite);
    }
}
