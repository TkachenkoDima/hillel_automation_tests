package Homeworks.Homework21;

import Homeworks.UIBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragNDropTestUI extends UIBaseTest {
    private final String url = "http://demo.guru99.com/test/drag_drop.html";

    @Test
    public void dragNDrop() {
        driver.get(url);
        WebElement bankElement = driver.findElement(By.id("credit2"));
        WebElement accountFieldDebitSide = driver.findElement(By.id("bank"));
        actions.dragAndDrop(bankElement, accountFieldDebitSide).perform();
        Assert.assertEquals(accountFieldDebitSide.getText(), "BANK",
        "Account field is not as expected");

        WebElement button5000 = driver.findElement(By.id("fourth"));
        WebElement amountFieldDebitSide = driver.findElement(By.id("amt7"));
        actions.dragAndDrop(button5000, amountFieldDebitSide).perform();
        Assert.assertEquals(amountFieldDebitSide.getText(), "5000",
                "Amount field is not as expected");

        WebElement salesElement = driver.findElement(By.id("credit1"));
        WebElement accountFieldCreditSide = driver.findElement(By.id("loan"));
        actions.dragAndDrop(salesElement, accountFieldCreditSide).perform();
        Assert.assertEquals(salesElement.getText(), "SALES",
                "Account field is not as expected");

        WebElement amountFieldCreditSide = driver.findElement(By.id("amt8"));
        actions.dragAndDrop(button5000, amountFieldCreditSide).perform();
        Assert.assertEquals(amountFieldCreditSide.getText(), "5000",
                "Amount field is not as expected");
    }
}
