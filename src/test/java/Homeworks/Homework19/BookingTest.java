package Homeworks.Homework19;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BookingTest extends BaseTest {
    private final String languageIcon = "//button[@data-modal-id='language-selection']";
    private final String englishLanguageIcon = "[lang|=en-us]";
    private final String searchDestinationButton = "#ss";
    private final String searchButton = "button[type='submit'";
    private final String monthsSelector = ".bui-calendar__wrapper";
    private final String monthSelector = ".bui-calendar__month";
    private final String daySelector = ".bui-calendar__date";
    private final String destinationField = "input[placeholder]";
    private final String checkInDateField = "div[data-placeholder='Check-in Date']";
    private final String checkOutDateField = "div[data-placeholder='Check-out Date'";
    private final String dateSelector = "div[class^=sb-searchbox__input]";
    private final String superbScoreFilter = "div[data-filters-item='review_score:review_score=90']";
    private final String superbScore = "div[aria-label^='Scored']";

    private final String url = "https://www.booking.com";
    private final String destination = "London";
    private final String year = "2021";
    private final String arrivalMonth = "December";
    private final String arrivalDate = "15";
    private final String arrivalWeekday = "Wednesday";
    private final String departureYear = "2021";
    private final String departureMonth = "December";
    private final String departureDate = "17";
    private final String departureWeekday = "Friday";

    @Test
    public void BookingPageTest() {
        driver.get(url);
        clickLanguageIcon();
        clickEnglishLanguageIcon();
        inputDestination();
        clickDatesSelection();
        selectDate(arrivalMonth, arrivalDate);
        selectDate(departureMonth, departureDate);
        clickSearch();
        clickSuperbScoreCheckBox();

        Assert.assertEquals(driver.findElement(By.cssSelector(destinationField)).getAccessibleName(), destination,
                "Destination is not as expected");
        Assert.assertEquals(driver.findElement(By.cssSelector(checkInDateField)).getText(),
                arrivalWeekday + ", " + arrivalMonth + " " + arrivalDate + ", " + year,
                "Check in date is not as expected");
        Assert.assertEquals(driver.findElement(By.cssSelector(checkOutDateField)).getText(),
                departureWeekday + ", " + departureMonth + " " + departureDate + ", " + departureYear,
                "Check out date is not as expected");

        checkOnlySuperbScoreHotels();
    }

    public void clickLanguageIcon() {
        driver.findElement(By.xpath(languageIcon)).click();
    }

    public void clickEnglishLanguageIcon() {
        driver.findElement(By.cssSelector(englishLanguageIcon)).click();
    }

    public void inputDestination() {
        driver.findElement(By.cssSelector(searchDestinationButton)).sendKeys(destination);
    }

    public void clickDatesSelection() {
        driver.findElement(By.cssSelector(dateSelector)).click();
    }

    public void clickSuperbScoreCheckBox() {
        driver.findElement(By.cssSelector(superbScoreFilter)).click();
    }

    public void clickSearch() {
        driver.findElement(By.cssSelector(searchButton)).click();
    }

    public void selectDate(String monthFind, String dateFind) {
        List<WebElement> month = driver.findElements(By.cssSelector(monthsSelector));
        for (WebElement monthArrival : month) {
            if (monthArrival.findElement(By.cssSelector(monthSelector)).getText().equals(monthFind + " " + year)) {
                List<WebElement> days = monthArrival.findElements(By.cssSelector(daySelector));
                for (WebElement arrivalDay : days) {
                    if (arrivalDay.getText().equals(dateFind)) {
                        arrivalDay.click();
                        break;
                    }
                }
            }
        }
    }

    public void checkOnlySuperbScoreHotels() {
        List<WebElement> hotelsScore = driver.findElements(By.cssSelector(superbScore));
        for (WebElement score : hotelsScore) {
            double minScore = 9.0;
                Assert.assertTrue(Double.parseDouble((score.getText())) >= minScore,
                        "Hotel score is not as expected");

            System.out.println(score.getText());
        }
    }
}