package Homework16;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringReverseTest {

    StringReverse stringReverse;

    @Test
    public void testStringReverse() {
        stringReverse = new StringReverse();
        String expectedResult = "Hello world";
        String actualResult = stringReverse.reversedString();
        Assert.assertNotEquals(actualResult, expectedResult,
                "Not actual result, go refactor, dummy");
        Assert.assertNotNull(expectedResult,
                "yup, assert not null!");
    }

    @Test
    public void testStringReverse1() {
        stringReverse = new StringReverse();
        String expectedResult = "dlroW olleH";
        String actualResult = stringReverse.reversedString();
        Assert.assertEquals(actualResult, expectedResult,
                "Not actual result, go refactor, dummy");
    }
}
