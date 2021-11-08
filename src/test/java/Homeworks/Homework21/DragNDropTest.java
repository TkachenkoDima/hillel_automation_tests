package Homeworks.Homework21;

import Homeworks.BaseTest;
import org.testng.annotations.Test;

public class DragNDropTest extends BaseTest {
    private final String url = "http://demo.guru99.com/test/drag_drop.html";

    @Test
    public void dragNDrop() {
        driver.get(url);

    }
}
