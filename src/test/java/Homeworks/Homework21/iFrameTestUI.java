package Homeworks.Homework21;

import Homeworks.UIBaseTest;
import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class iFrameTestUI extends UIBaseTest {
    private final String url = "http://the-internet.herokuapp.com/iframe";
    private final String italicButton = "button[aria-label='Italic']";
    private final String italicButtonState = "aria-pressed";
    private final String inputField = "body#tinymce";
    private final String inputText = "may the force be with you";
    private final String inputFrame = "mce_0_ifr";
    private final String header = "h3";
    private final String headerText = "An iFrame containing the TinyMCE WYSIWYG Editor";

    @Test
    public void iFrameTextInput() {
        driver.get(url);
        driver.findElement(By.cssSelector(italicButton)).click();
        Assert.assertTrue((driver.findElement(By.cssSelector(italicButton)).getAttribute(italicButtonState).contains("true")),
                "Button is not pressed");
        driver.switchTo().frame(inputFrame);
        WebElement frame = driver.findElement(By.cssSelector(inputField));
        clearInputWithKeys(frame);
        driver.findElement(By.cssSelector(inputField)).sendKeys(inputText);
        Assert.assertEquals(driver.findElement(By.cssSelector(inputField)).getText(), inputText,
                "Input text is not as expected");
        driver.switchTo().defaultContent();
        Assert.assertEquals(driver.findElement(By.cssSelector(header)).getText(), headerText,
                "Header text is not as expected");
    }

    public void clearInputWithKeys(WebElement frame){
        if (OS.isFamilyMac()) {
            frame.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            frame.sendKeys(Keys.DELETE);
        } else {
            frame.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            frame.sendKeys(Keys.BACK_SPACE);
        }
    }
}
