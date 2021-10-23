package Classwork18;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EvenNumberTest {

    EvenNumber evenNumber;

    @BeforeTest
    public void beforeTest() {
        evenNumber = new EvenNumber();
    }

    @AfterTest
    public void afterTestMessage() {
        System.out.println("Calculator test passed");
    }

    @Test
    public void evenNumberTestAssertTrue(){
        Assert.assertTrue(evenNumber.isEvenNumber(6),
                "Not even number, try again");
    }

    @Test
    public void evenNumberTestAssertFalse(){
        Assert.assertFalse(evenNumber.isEvenNumber(7),
                "Assert not true, it's ok");
    }
}
