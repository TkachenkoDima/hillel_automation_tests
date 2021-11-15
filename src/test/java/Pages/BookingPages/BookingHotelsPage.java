package Pages.BookingPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookingHotelsPage extends BasePage {
    By superbScoreFilter = By.cssSelector("div[data-filters-item='review_score:review_score=90']");
    By superbScore = By.cssSelector("div[aria-label^='Scored']");
    By destinationField = By.cssSelector("input[placeholder]");
    By checkInDateField = By.cssSelector("div[data-placeholder='Check-in Date']");
    By checkOutDateField = By.cssSelector("div[data-placeholder='Check-out Date'");

    public BookingHotelsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSuperbScore() {
       return driver.findElements(superbScore);
    }

    public String getCheckInDate() {
        return driver.findElement(checkInDateField).getText();
    }

    public String getCheckOutDate() {
        return driver.findElement(checkOutDateField).getText();
    }

    public BookingHotelsPage clickSuperbScoreCheckBox() {
        driver.findElement(superbScoreFilter).click();
        return this;
    }

    public String getDestinationFieldAccessibleName() {
        return driver.findElement(destinationField).getAccessibleName();
    }
}
