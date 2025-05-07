package Lesson16.priority;

import org.testng.annotations.Test;

public class PriorityTestTestNG {

    @Test(priority = 1)
    public void g() {
    }

    @Test(priority = 2)
    public void f() {
    }

    @Test(priority = 3)
    public void e() {
    }

    @Test(priority = 4)
    public void d() {
    }

    @Test(priority = 5)
    public void c() {
    }

    @Test(priority = 6)
    public void b() {
    }

    @Test(priority = 6)
    public void a() {
    }

}
