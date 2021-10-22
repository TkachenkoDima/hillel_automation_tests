package Homework16;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EvenNumberTest {

    EvenNumber evenNumber;

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
