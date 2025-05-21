package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.Dimension;

public class ElementUtils {

    public static void compareElementsPositionAndSize(WebElement elem1, WebElement elem2) {
        Point loc1 = elem1.getLocation();
        Point loc2 = elem2.getLocation();

        Dimension size1 = elem1.getSize();
        Dimension size2 = elem2.getSize();

        if (loc1.getY() < loc2.getY()) {
            System.out.println("First element is positioned higher than second element.");
        } else if (loc1.getY() > loc2.getY()) {
            System.out.println("Second element is positioned higher than first element.");
        } else {
            System.out.println("Both elements are positioned at the same vertical level.");
        }

        if (loc1.getX() < loc2.getX()) {
            System.out.println("First element is positioned more to the left than second element.");
        } else if (loc1.getX() > loc2.getX()) {
            System.out.println("Second element is positioned more to the left than first element.");
        } else {
            System.out.println("Both elements are positioned at the same horizontal level.");
        }

        int area1 = size1.getWidth() * size1.getHeight();
        int area2 = size2.getWidth() * size2.getHeight();

        if (area1 > area2) {
            System.out.println("First element occupies more space than second element.");
        } else if (area1 < area2) {
            System.out.println("Second element occupies more space than first element.");
        } else {
            System.out.println("Both elements occupy the same amount of space.");
        }
    }
}
