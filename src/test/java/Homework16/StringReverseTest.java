package Homework16;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StringReverseTest {

    StringReverse stringReverse;

    @BeforeTest
    public void beforeTest() {
        stringReverse = new StringReverse();
    }

    @AfterTest
    public void afterTestMessage() {
        System.out.println("String reverse test passed");
    }

    @Test
    public void testStringReverse() {
        String expectedResult = "Hello world";
        String actualResult = stringReverse.reversedString();
        Assert.assertNotEquals(actualResult, expectedResult,
                "Not actual result, go refactor, dummy");
        Assert.assertNotNull(expectedResult,
                "yup, assert not null!");
    }

    @Test
    public void testStringReverse1() {
        String expectedResult = "dlroW olleH";
        String actualResult = stringReverse.reversedString();
        Assert.assertEquals(actualResult, expectedResult,
                "Not actual result, go refactor, dummy");
    }
}
