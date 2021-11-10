package Homeworks.Homework21;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DragNDropTest extends BaseTest {
    private final String url = "http://demo.guru99.com/test/drag_drop.html";
    private final By amountFirstAreaToDrop = By.cssSelector("[itemtype] td:nth-of-type(1) td:nth-of-type(2) .placeholder");
    private final By salesAreaToDrop = By.id("loan");
    private final By amountSecondAreaToDrop = By.cssSelector("[itemtype] td:nth-of-type(2) td:nth-of-type(2) .placeholder");

    @Test
    public void dragNDrop() {
        driver.get(url);


    }
}
