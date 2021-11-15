package Pages.RozetkaPages;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RozetkaProductsPage extends BasePage {
    By productTitle = By.cssSelector(".content_type_catalog .goods-tile__heading");
    By mobilePhonesFiltersLocator = By.xpath("//a[contains(@href, 'mobile-phones') and @class='categories-filter__link ng-star-inserted']");
    By submitPriceButton = By.cssSelector("button[type='submit']");
    By minPriceInput = By.cssSelector("input[formcontrolname='min']");
    By maxPriceInput = By.cssSelector("input[formcontrolname='max']");
    By appleCheckBox = By.cssSelector("label[for='Apple']");
    By huaweiCheckBox = By.cssSelector("label[for='Huawei']");
    By memoryCheckBox = By.cssSelector("label[for='128 ГБ']");
    By whiteColorCheckBox = By.cssSelector("label[for='Белый']");
    By itemPrice =  By.cssSelector("div[class^='goods-tile__price--old']");
    By discountedPrice = By.cssSelector("span.goods-tile__price-value");
    By itemTitle = By.cssSelector("span.goods-tile__title");
    By monitorItemCard = By.cssSelector("li[class^='catalog-grid__cell']");

    public RozetkaProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getDiscountedPrice() {
        return driver.findElements(discountedPrice);
    }
    public void clickOnProductWithPriceLessThan(int price) {
        List<WebElement> monitors = driver.findElements(monitorItemCard);
        for (WebElement monitor : monitors) {
            String monitorPrice = monitor.findElement(itemPrice).getText().replaceAll("[^0-9]", "");
            if (Integer.parseInt(monitorPrice) < price) {
                monitor.click();
                break;
            }
        }
    }

    public List<WebElement> getProductTitle() {
        return driver.findElements(itemTitle);
    }

    public void setPriceRange(String minPrice, String maxPrice) {
        driver.findElement(minPriceInput).clear();
        driver.findElement(minPriceInput).sendKeys(minPrice);
        driver.findElement(maxPriceInput).clear();
        driver.findElement(maxPriceInput).sendKeys(maxPrice);
    }

    public RozetkaProductsPage clickWhiteColorFilterCheckBox() {
        driver.findElement(whiteColorCheckBox).click();
        return this;
    }

    public RozetkaProductsPage clickSubmitPriceRangeButton() {
        driver.findElement(submitPriceButton).click();
        return this;
    }

    public RozetkaProductsPage click128GBMemoryCheckBox() {
        driver.findElement(memoryCheckBox).click();
        return this;
    }

    public RozetkaProductsPage clickAppleBrandCheckbox() {
        driver.findElement(appleCheckBox).click();
        return this;
    }

    public RozetkaProductsPage clickHuaweiBrandCheckbox() {
        driver.findElement(huaweiCheckBox).click();
        return this;
    }

    public RozetkaProductsPage clickMobilePhonesCategory() {
        driver.findElement(mobilePhonesFiltersLocator).click();
        return this;
    }
}
