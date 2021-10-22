package Homework16;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class EvenNumberTest {

    EvenNumber evenNumber;

    @AfterTest
    public void afterTestMessage() {
        System.out.println("Calculator test passed");
    }

    @Test
    public void evenNumberTestAssertTrue(){
        evenNumber = new EvenNumber();
        Assert.assertTrue(evenNumber.isEvenNumber(6),
                "Not even number, try again");
    }

    @Test
    public void evenNumberTestAssertFalse(){
        evenNumber = new EvenNumber();
        Assert.assertFalse(evenNumber.isEvenNumber(7),
                "Assert not true, it's ok");
    }
}
