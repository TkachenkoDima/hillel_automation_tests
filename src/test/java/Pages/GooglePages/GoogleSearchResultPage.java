package Pages.GooglePages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchResultPage extends BasePage {
    By shopLink = By.cssSelector("cite[role='text']");
    By nextPage = By.xpath("//span[text()='Следующая']");

    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void clickNextPage() {
        driver.findElement(nextPage).click();
    }

    public void checkResults(String expectedWebsite) {
        int i = 0;
        while (i <= 5) {
            List<WebElement> searchResults = driver.findElements(shopLink);
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
}
