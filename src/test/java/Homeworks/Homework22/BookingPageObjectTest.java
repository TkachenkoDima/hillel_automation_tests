package Homeworks.Homework22;

import Homeworks.BaseTest;
import Pages.BookingPages.BookingHotelsPage;
import Pages.BookingPages.BookingMainPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BookingPageObjectTest extends BaseTest {
    private final String url = "https://booking.com/";
    private final String destination = "London";

    BookingMainPage bookingMainPage;
    BookingHotelsPage bookingHotelsPage;

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        driver.get(url);
    }

    @Test
    public void bookingHotelsScoreTest() {
        bookingMainPage = new BookingMainPage(driver);
        bookingHotelsPage = new BookingHotelsPage(driver);

        bookingMainPage.clickLanguageIcon()
        .clickEnglishLanguageIcon()
        .inputDestination()
        .clickDatesSelection();
        bookingMainPage.selectDate("December", "15");
        bookingMainPage.selectDate("December", "17");
        bookingMainPage.clickSearch();
        bookingHotelsPage.clickSuperbScoreCheckBox();

        Assert.assertEquals(bookingHotelsPage.getDestinationFieldAccessibleName(), destination,
                "Destination is not as expected");
        Assert.assertEquals(bookingHotelsPage.getCheckInDate(), bookingMainPage.getArrivalWeekday() + ", " + bookingMainPage.getArrivalMonth() + " " + bookingMainPage.getArrivalDate() + ", " + bookingMainPage.getArrivalYear(),
                "Check in date is not as expected");
        Assert.assertEquals(bookingHotelsPage.getCheckOutDate(), bookingMainPage.getDepartureWeekday() + ", " + bookingMainPage.getDepartureMonth() + " " + bookingMainPage.getDepartureDate() + ", " + bookingMainPage.getDepartureYear(),
                "Check out date is not as expected");

        checkOnlySuperbScoreHotels();
    }

    public void checkOnlySuperbScoreHotels() {
        List<WebElement> hotelsScore = bookingHotelsPage.getSuperbScore();
        for (WebElement score : hotelsScore) {
            double minScore = 9.0;
            Assert.assertTrue(Double.parseDouble((score.getText())) >= minScore,
                    "Hotel score is not as expected");
            System.out.println(score.getText());
        }
    }
}
