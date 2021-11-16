package Pages.SportcheckPages;

import Pages.BasePage;
import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SportCheckPage extends BasePage {
    By colors = By.cssSelector("a.product-detail__color-option");
    By selectedColor = By.cssSelector("a[class*='selected']");
    By size = By.cssSelector("a[data-control-type='size']");
    By outOfStockSize = By.cssSelector("a[class*='out-of-stock']");
    By quantityField = By.cssSelector("input#qty-selector");
    By addToCartButton = By.cssSelector("button[class^='add-cart']");
    By plusQTYButton = By.cssSelector("div[class*='btn--up']");
    By modalWindow = By.cssSelector("header-cart__container");
    By cartIcon = By.cssSelector("span.header-cart__text");
    By productTitle = By.cssSelector("h1.global-page-header__title");
    By cartProductTitle = By.cssSelector("h2.cart-item__title");
    By cartItemQuantity = By.cssSelector("dl.cart-item__detail");
    By inStockLabel = By.cssSelector("div.prd-in-stock-label");

    public SportCheckPage(WebDriver driver) {
        super(driver);
    }

    public void selectColor() {
        List<WebElement> colorsList = driver.findElements(colors);
        for (WebElement color : colorsList) {
            if (!color.getAttribute("class").contains("selected")) {
                color.click();
                break;
            }
            break;
        }
    }

    public void selectSize() {
        List<WebElement> sizes = driver.findElements(size);
        for (WebElement size : sizes) {
            if (!size.getAttribute("class").contains("out-of-stock")) {
                size.click();
                return;
            }
        }
    }

    public boolean isCartModalWindowDisplayed() {
        return driver.findElement(modalWindow).isDisplayed();
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }

    public void clearInputWithKeys(WebElement element){
        if (OS.isFamilyMac()) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            element.sendKeys(Keys.DELETE);
        } else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void setQuantity(String quantity) {
        driver.findElement(quantityField).sendKeys(quantity);
    }

    public void clickQuantity(int quantity) {
        for (int i = 0; i < quantity; i++) {
            driver.findElement(plusQTYButton).click();
        }
    }

    public void moveToCartIcon() {
        actions.moveToElement(driver.findElement(cartIcon)).perform();
    }

    public String getQuantity() {
        return driver.findElement(quantityField).getText();
    }

    public String getCartQuantity() {
        return driver.findElement(cartItemQuantity).getText();
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public String getCartItemTitle() {
        return driver.findElement(cartProductTitle).getText();
    }

    public boolean isInStockLabelDisplayed() {
        return driver.findElement(inStockLabel).isDisplayed();
    }
}
