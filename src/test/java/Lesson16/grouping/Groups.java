package Lesson16.grouping;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Groups {

    @Test(groups = "first", priority = 1)
    public void one() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 2)
    public void two() {
        assertTrue(true);
    }

    @Test(groups = "first", priority = 3)
    public void three() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 4)
    public void four() {
        assertTrue(true);
    }

    @Test(groups = "first", priority = 5)
    public void five() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 6)
    public void six() {
        assertTrue(true);
    }

    @Test(groups = "first", priority = 7)
    public void seven() {
        assertTrue(true);
    }

    @Test(groups = "second", priority = 8)
    public void eight() {
        assertTrue(true);
    }

}
