package Homeworks.Homework21;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SwitchTabsText extends BaseTest {
    private final String url = "https://demoqa.com/browser-windows";
    private final String tabUrl = "https://demoqa.com/sample";
    private final String newButton = "button#tabButton";

    @Test
    public void tabsSwitch() {
        driver.get(url);
        clickNewTab();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), tabUrl,
                "Current URL is not as expected");

        driver.switchTo().window(tabs.get(0));
        clickNewTab();

        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertEquals(allTabs.size(), 3,
                "Not an expected amount of tabs are opened");

        driver.switchTo().window(allTabs.get(2));
        driver.close();
        allTabs.remove(2);

        driver.switchTo().window(allTabs.get(1));
        driver.close();
        allTabs.remove(1);

        driver.switchTo().window(allTabs.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), url,
                "You are not at the main tab");
        Assert.assertEquals(allTabs.size(), 1,
                "Not only one tab is opened");
    }

    public void clickNewTab() {
        driver.findElement(By.cssSelector(newButton)).click();
    }
}
