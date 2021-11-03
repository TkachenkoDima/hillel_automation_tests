package Homeworks.Homework19;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.WatchEvent;
import java.util.List;

public class BookingTest extends BaseTest {
    private final String languageIcon = "//button[@data-modal-id='language-selection']";
    private final String checkOutButton = "div[data-mode='checkout']";
    private final String englishLanguageIcon = "[lang|=en-us]";
    private final String searchDestinationButton = "#ss";
    private final String searchButton = "button[type='submit'";
    private final String monthsSelector = ".bui-calendar__wrapper";
    private final String monthSelector = ".bui-calendar__month";
    private final String daySelector = ".bui-calendar__date";
    private final String nextMonthIcon = ".bui-calendar__control--next";
    private final String destinationField = "input[placeholder]";
    private final String checkInDateField = "div[data-placeholder='Check-in Date']";
    private final String checkOutDateField = "div[data-placeholder='Check-out Date'";
    private final String dateSelector = "div[class^=sb-searchbox__input]";

    private final String reviewScore = "div[data-filters-item='review_score:review_score=90']";

    private final String url = "https://www.booking.com";
    private final String destination = "London";
    private final String arrivalYear = "2021";
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
        driver.findElement(By.xpath(languageIcon)).click();
        driver.findElement(By.cssSelector(englishLanguageIcon)).click();
        driver.findElement(By.cssSelector(searchDestinationButton)).sendKeys(destination);
        driver.findElement(By.cssSelector(dateSelector)).click();
        selectArrivalDate();
        selectDepartureDate();
        driver.findElement(By.cssSelector(searchButton)).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(destinationField)).getAccessibleName(), destination,
                "Destination is not as expected");
        Assert.assertEquals(driver.findElement(By.cssSelector(checkInDateField)).getText(),
                arrivalWeekday + ", " + arrivalMonth + " " + arrivalDate + ", " + arrivalYear,
                "Check in date is not as expected");
        Assert.assertEquals(driver.findElement(By.cssSelector(checkOutDateField)).getText(),
                departureWeekday + ", " + departureMonth + " " + departureDate + ", " + departureYear,
                "Check out date is not as expected");

        driver.findElement(By.cssSelector(reviewScore)).click();

        List<WebElement> score = driver.findElements(By.cssSelector("div[class^='hotellist_wrap']"));
//        div[aria-label]

        System.out.println("kek");
    }

    public void selectArrivalDate() {
        List<WebElement> month = driver.findElements(By.cssSelector(monthsSelector));
        for (WebElement monthArrival : month) {
            if (monthArrival.findElement(By.cssSelector(monthSelector)).getText().equals(arrivalMonth + " " + arrivalYear)) {
                List<WebElement> days = monthArrival.findElements(By.cssSelector(daySelector));
                for (WebElement arrivalDay : days) {
                    if (arrivalDay.getText().equals(arrivalDate)) {
                        arrivalDay.click();
                        break;
                    }
                }
            }
        }
    }

    public void selectDepartureDate() {
        List<WebElement> month = driver.findElements(By.cssSelector(monthsSelector));
        for (WebElement monthDeparture : month) {
            if (monthDeparture.findElement(By.cssSelector(monthSelector)).getText().equals(departureMonth + " " + departureYear)) {
                List<WebElement> days = monthDeparture.findElements(By.cssSelector(daySelector));
                for (WebElement departureDay : days) {
                    if (departureDay.getText().equals(departureDate)) {
                        departureDay.click();
                        break;
                    }
                }
            }
        }
    }
}