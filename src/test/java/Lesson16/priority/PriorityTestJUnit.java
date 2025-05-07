package Lesson16.priority;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriorityTestJUnit {

    @Test
    @Order(1)
    public void g(){}

    @Test
    @Order(2)
    public void f(){}

    @Test
    @Order(3)
    public void e(){}

    @Test
    @Order(4)
    public void d(){}

    @Test
    @Order(5)
    public void c(){}

    @Test
    @Order(6)
    public void b(){}

    @Test
    @Order(7)
    public void a(){}

}
