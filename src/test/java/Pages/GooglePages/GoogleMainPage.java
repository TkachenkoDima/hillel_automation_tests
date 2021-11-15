package Pages.GooglePages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleMainPage extends BasePage {
    By searchField = By.cssSelector("input[name='q']");
    By searchButton = By.cssSelector("input[type='submit']");

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }

    public void performSearch(String searchText) {
        enterTextIntoSearchField(searchText);
        clickSearchButton();
    }

    public GoogleMainPage enterTextIntoSearchField(String searchText) {
        driver.findElement(searchField).sendKeys(searchText);
        return this;
    }

    public GoogleMainPage clickSearchButton() {
        driver.findElement(searchButton).click();
        return this;
    }
}
