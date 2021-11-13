package Pages.RozetkaPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RozetkaMainPage extends BasePage {
    By searchField = By.cssSelector("input[name=search]");
    By searchButton = By.cssSelector(".button.button_color_green");
    By computerAndLaptopsLocator = By.linkText("Ноутбуки и компьютеры");
    By compareIcon = By.cssSelector("span[class^='counter']");
    By compareModal = By.cssSelector("li[class^='comparison-modal']");
    By monitorsCategoryLocator = By.cssSelector("a[title='Мониторы']");
    By compareItemsList = By.cssSelector("li[class^='products-grid__cell']");
    By cartCounter = By.cssSelector("span.counter");
    By oldPrice = By.cssSelector("div.product__price--old");

    public RozetkaMainPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCompareItemList() {
       return driver.findElements(compareItemsList);
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartCounter).isEmpty();
    }

    public void clickMonitors() {
        driver.findElement(monitorsCategoryLocator).click();
    }

    public void clickCompareItemsInModal() {
       driver.findElement(compareModal).click();
    }

    public void clickCompareHeaderIcon() {
        driver.findElement(compareIcon).click();
    }

    public String getCartLabelText() {
        return driver.findElement(cartCounter).getText();
    }

    public void clickComputerAndLaptopsCategory() {
        driver.findElement(computerAndLaptopsLocator).click();
    }

    public void performSearch(String searchText) {
        enterTextIntoSearchField(searchText);
        clickSearchButton();
    }

    public void enterTextIntoSearchField(String searchText) {
        driver.findElement(searchField).sendKeys(searchText);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
}