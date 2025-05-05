import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class ElementUtils {  

    public static void compareElements(WebElement element1, WebElement element2) {
        Point location1 = element1.getLocation();
        Point location2 = element2.getLocation();

        Dimension size1 = element1.getSize();
        Dimension size2 = element2.getSize();

        if (location1.getY() < location2.getY()) {
            System.out.println("Element 1 is higher on the page.");
        } else if (location1.getY() > location2.getY()) {
            System.out.println("Element 2 is higher on the page.");
        } else {
            System.out.println("Both elements are at the same vertical position.");
        }

        if (location1.getX() < location2.getX()) {
            System.out.println("Element 1 is more to the left.");
        } else if (location1.getX() > location2.getX()) {
            System.out.println("Element 2 is more to the left.");
        } else {
            System.out.println("Both elements are at the same horizontal position.");
        }

        int area1 = size1.getHeight() * size1.getWidth();
        int area2 = size2.getHeight() * size2.getWidth();

        if (area1 > area2) {
            System.out.println("Element 1 takes up more space.");
        } else if (area1 < area2) {
            System.out.println("Element 2 takes up more space.");
        } else {
            System.out.println("Both elements occupy the same space.");
        }
    }
}
