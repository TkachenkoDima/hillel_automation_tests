package Pages.GooglePages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchResultPage extends BasePage {
    By siteName = By.cssSelector("#res .g");
    By nextPage = By.cssSelector("a[id='pnnext']");
    By searchField = By.cssSelector("input[role='combobox']");

    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void clickNextPage() {
        driver.findElement(nextPage).click();
    }

    public String getSearchFieldText() {
        return driver.findElement(searchField).getAttribute("value");
    }

    public void check(String expectedWebsite) {
        int i = 0;
        while (i <= 5) {
            List<WebElement> searchResults = driver.findElements(siteName);
            for (WebElement results : searchResults) {
                String link = results.getText();
                if (link.contains(expectedWebsite)) {
                    System.out.println("Found " + link);
                    return;
                }
            }
            i++;
            clickNextPage();
            if (i == 5) {
                System.out.println("was not found on first 5 pages.");
            }
        }
    }


    public boolean isSiteNameFoundOnFirst5Pages(String expectedWebsite) {
        int pageNum = 0;
        for (int i = 1; i <= 5; i++) {
            List<WebElement> searchResults = driver.findElements(siteName);
            for (WebElement results : searchResults) {
                String link = results.getText();
                if (link.contains(expectedWebsite)) {
                    pageNum = i;
                    System.out.println("Found on page: " + pageNum);
                    return true;
                }
            }
            if (!(i == 5)) {
                clickNextPage();
            }
        }
        return false;
    }
}