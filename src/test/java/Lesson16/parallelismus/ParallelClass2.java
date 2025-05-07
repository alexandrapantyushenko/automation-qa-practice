package Lesson16.parallelismus;

import org.testng.annotations.Test;

public class ParallelClass2 {

    @Test
    public void parallel6() throws InterruptedException {
        Thread.sleep(2000);
        assert true;
    }

    @Test
    public void parallel7() throws InterruptedException{
        Thread.sleep(2000);
        assert true;
    }

    @Test
    public void parallel8() throws InterruptedException{
        Thread.sleep(2000);
        assert true;
    }

    @Test
    public void parallel9() throws InterruptedException{
        Thread.sleep(2000);
        assert true;
    }

    @Test
    public void parallel10() throws InterruptedException{
        Thread.sleep(2000);
        assert true;
    }
}


