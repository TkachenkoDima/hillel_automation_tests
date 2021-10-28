package Homeworks.Homework18;

import Homeworks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Guru99Test extends BaseTest {
    String url = "https://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String validLogin = "1303";
    String invalidLogin = "Kek";
    String validPassword = "Guru99";
    String alertInvalidLoginPassText = "User or Password is not valid";
//    String invalidPassword = "123";
//    String alertSuccessfulLoggedOutText = "You Have Succesfully Logged Out!!";

    @Test
    public void blankFieldsMessages() {
        driver.get(url);
        WebElement loginField = driver.findElement(By.name("uid"));
        loginField.click();
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.click();
        loginField.click();
        WebElement blankUsername = driver.findElement(By.id("message23"));
        Assert.assertTrue(blankUsername.isDisplayed(),
                "Blank username message not displayed!");
        WebElement blankPass = driver.findElement(By.id("message18"));
        blankPass.isDisplayed();
        Assert.assertTrue(blankPass.isDisplayed(),
                "Blank pass message not displayed!");
    }

    @Test
    public void loginWithEmptyFields() {
        driver.get(url);
        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();
        Assert.assertEquals(driver.switchTo().alert().getText(), alertInvalidLoginPassText);
    }

    @Test
    public void loginWithInvalidUsername() {
        driver.get(url);
        WebElement loginField = driver.findElement(By.name("uid"));
        loginField.sendKeys(invalidLogin);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(validPassword);
        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();
        Assert.assertEquals(driver.switchTo().alert().getText(), alertInvalidLoginPassText);
    }

    @Test
    public void loginWithExtraSpaces() {
        driver.get(url);
        WebElement loginField = driver.findElement(By.name("uid"));
        loginField.sendKeys(validLogin);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(validPassword+"              ");
        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), url,
                "Current url is not as Expected!");
        //Test will be failed, bug for extra spaces in pass field found.
    }

//    @Test
//    public void loginWithInvalidPassword() {
//        driver.get(url);
//        WebElement loginField = driver.findElement(By.name("uid"));
//        loginField.sendKeys(validLogin);
//        WebElement passwordField = driver.findElement(By.name("password"));
//        passwordField.sendKeys(invalidPassword);
//        WebElement loginButton = driver.findElement(By.name("btnLogin"));
//        loginButton.click();
//        Assert.assertEquals(driver.switchTo().alert().getText(), alertInvalidLoginPassText);
//        driver.switchTo().alert().accept();
//        Assert.assertEquals(driver.getCurrentUrl(), url);
//    }

//    @Test
//    public void loginWithValidCredentials() {
//        driver.get(url);
//        WebElement loginField = driver.findElement(By.name("uid"));
//        loginField.sendKeys(validLogin);
//        WebElement passwordField = driver.findElement(By.name("password"));
//        passwordField.sendKeys(validPassword);
//        WebElement loginButton = driver.findElement(By.name("btnLogin"));
//        loginButton.click();
//        String expectedTitle = "Guru99 Bank";
//        WebElement pageTitle = driver.findElement(By.className("barone"));
//        Assert.assertEquals(pageTitle.getText(), expectedTitle);
//        WebElement logoutButton = driver.findElement(By.cssSelector("[href^=Logout]"));
//        logoutButton.click();
//        String partialAlertTest = "Logged Out";
//        Assert.assertEquals(driver.switchTo().alert().getText(), alertSuccessfulLoggedOutText);
//        Assert.assertTrue(driver.switchTo().alert().getText().contains(partialAlertTest));
//        driver.switchTo().alert().accept();
//        Assert.assertEquals(driver.getCurrentUrl(), url);
//    }
}

