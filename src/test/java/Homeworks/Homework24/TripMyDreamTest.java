package Homeworks.Homework24;

import com.codeborne.selenide.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class TripMyDreamTest {

    String departureCity = "Berlin";
    String arrivalCity = "London";
    String arrivalDate = "2021-12-15";
    String departureDate = "2021-12-19";
    String departureDay = "15 Dec, Wed";
    String arrivalDay = "19 Dec, Sun";

    @Test
    public void selectTickets() {

        open("https://en.tripmydream.com/");

        $("div.s-form__place-btn").click();
        $("input[data-placeholder*='Origin city']").val(departureCity);
        $("label[data-code='BER'").click();
        $("input[data-placeholder*='Destination city']").val(arrivalCity);
        $("label[data-code='LCY'").click();
        $("td[data-date='" + arrivalDate + "']").click();
        $("td[data-date='" + departureDate + "']").click();
        $("div[class*='hc-sb-submit']").click();
        switchTo().window(0);
        closeWindow();
        switchTo().window(0);
        sleep(2000);
        $("div[class^='popup__close']").click();
        $$("div[class^='s-form__input-wrap']").shouldHave(CollectionCondition.texts(departureCity, arrivalCity));
        $("div[class^='result__sform-row hc-calendar'").shouldHave(text(departureDay)).shouldHave(text(arrivalDay));
        checkFlightData();
    }

    public void checkFlightData() {
        ElementsCollection flightDetails = $$("div.flight__content");
        for (SelenideElement details : flightDetails) {
            details.shouldHave(text(departureCity)).shouldHave(text(arrivalCity));
            details.shouldHave(text(departureDay)).shouldHave(text(arrivalDay));
            System.out.println(details);
        }
    }
}