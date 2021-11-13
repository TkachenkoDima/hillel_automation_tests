package Pages.RozetkaPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RozetkaProductSinglePage extends BasePage {
    By selectedProductTitle = By.cssSelector("h1.product__title");
    By buyButton = By.cssSelector("span.buy-button__label");
    By closeModalWindowButton = By.cssSelector("button.modal__close");
    By monitorTitle = By.cssSelector("h1.product__title");
    By monitorPrice = By.cssSelector("p[class^='product-prices__small']");
    By compareButton = By.cssSelector("button[class^='compare-button']");
    By oldPrice = By.cssSelector("div.product__price--old");

    public RozetkaProductSinglePage(WebDriver driver) {
        super(driver);
    }

    public void clickCompareSingleItem() {
       driver.findElement(compareButton).click();
    }

    public String getMonitorName() {
        return driver.findElement(monitorTitle).getText();
    }

    public List<WebElement> getOldMonitorPrice() {
        return driver.findElements(oldPrice);
    }

    public int getMonitorPrice() {
        return Integer.parseInt(String.valueOf(driver.findElement(monitorPrice).getText()).replaceAll("[^0-9]", ""));
    }
}
