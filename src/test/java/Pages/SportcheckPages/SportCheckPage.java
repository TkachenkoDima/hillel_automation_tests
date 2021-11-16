package Pages.SportcheckPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SportCheckPage extends BasePage {
    By colors = By.cssSelector("div[class='product-detail__select']");
    By size = By.cssSelector("a[data-control-type='size']");
    By count = By.cssSelector("input#qty-selector");
    By buyButton = By.cssSelector("button[class^='add-cart']");


    public SportCheckPage(WebDriver driver) {
        super(driver);
    }

    public void selectColor() {
        List<WebElement> colorsList = driver.findElements(colors);
        for (WebElement color : colorsList) {
//            if color
        }
    }

    public void clickSize() {

    }
}
