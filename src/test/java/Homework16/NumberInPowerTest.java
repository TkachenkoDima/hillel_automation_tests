package Homework16;

import Homework16.NumberInPower;
import org.testng.annotations.*;
import org.testng.Assert;

public class NumberInPowerTest {

    NumberInPower numberInPower;

    @Test
    public void testPowered () {
        numberInPower = new NumberInPower();
        int expectedResult = 256;
        int actualResult = numberInPower.powered(4, 4);

        Assert.assertEquals(actualResult, expectedResult,
                "Not actual result, go refactor, dummy");
    }

    @Test
    public void testPowered1 () {
        numberInPower = new NumberInPower();
        int actualResult = numberInPower.powered(4, 4);

        Assert.assertEquals(actualResult, 256,
                "Not actual result, go refactor, dummy");
    }
}
