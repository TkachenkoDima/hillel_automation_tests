package Pages.BookingPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BookingMainPage extends BasePage {
    By languageIcon = By.xpath("//button[@data-modal-id='language-selection']");
    By englishLanguageIcon = By.cssSelector("[lang|=en-us]");
    By searchDestinationButton = By.cssSelector("#ss");
    By searchButton = By.cssSelector("button[type='submit'");
    By monthsSelector = By.cssSelector(".bui-calendar__wrapper");
    By monthSelector = By.cssSelector(".bui-calendar__month");
    By daySelector = By.cssSelector(".bui-calendar__date");
    By dateSelector = By.cssSelector("div[class^=sb-searchbox__input]");

    private final String destination = "London";
    private final String year = "2021";
    private final String arrivalMonth = "December";
    private final String arrivalDate = "15";
    private final String arrivalWeekday = "Wednesday";
    private final String departureYear = "2021";
    private final String departureMonth = "December";
    private final String departureDate = "17";
    private final String departureWeekday = "Friday";

    public BookingMainPage(WebDriver driver) {
        super(driver);
    }

    public String getArrivalYear() {
        return year;
    }

    public String getArrivalMonth() {
        return arrivalMonth;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalWeekday() {
        return arrivalWeekday;
    }

    public String getDepartureYear() {
        return departureYear;
    }

    public String getDepartureMonth() {
        return departureMonth;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureWeekday() {
        return departureWeekday;
    }

    public BookingMainPage clickLanguageIcon() {
        driver.findElement(languageIcon).click();
        return this;
    }

    public BookingMainPage clickEnglishLanguageIcon() {
        driver.findElement(englishLanguageIcon).click();
        return this;
    }

    public BookingMainPage inputDestination() {
        driver.findElement(searchDestinationButton).sendKeys(destination);
        return this;
    }

    public BookingMainPage clickDatesSelection() {
        driver.findElement(dateSelector).click();
        return this;
    }

    public BookingMainPage clickSearch() {
        driver.findElement(searchButton).click();
        return this;
    }

    public void selectDate(String monthFind, String dateFind) {
        List<WebElement> month = driver.findElements(monthsSelector);
        for (WebElement monthArrival : month) {
            if (monthArrival.findElement(monthSelector).getText().equals(monthFind + " " + year)) {
                List<WebElement> days = monthArrival.findElements(daySelector);
                for (WebElement arrivalDay : days) {
                    if (arrivalDay.getText().equals(dateFind)) {
                        arrivalDay.click();
                        break;
                    }
                }
            }
        }
    }
}
