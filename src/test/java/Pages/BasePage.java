package Pages;

import org.apache.commons.exec.OS;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    JavascriptExecutor js;

    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        js = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
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


//    public void waitFor(String element) {
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));
//    }

}
