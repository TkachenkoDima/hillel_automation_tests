package Classwork18;

import org.testng.annotations.*;
import org.testng.Assert;

public class NumberInPowerTest {

    NumberInPower numberInPower;

    @BeforeTest
    public void beforeTest() {
        numberInPower = new NumberInPower();
    }

    @AfterTest
    public void afterTestMessage() {
        System.out.println("Number in power test passed");
    }

    @Test
    public void testPowered () {
        int expectedResult = 256;
        int actualResult = numberInPower.powered(4, 4);

        Assert.assertEquals(actualResult, expectedResult,
                "Not actual result, go refactor, dummy");
    }

    @Test
    public void testPowered1 () {
        int actualResult = numberInPower.powered(4, 4);

        Assert.assertEquals(actualResult, 256,
                "Not actual result, go refactor, dummy");
    }
}
