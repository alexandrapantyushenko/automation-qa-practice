package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DragAndDropPage {

    private WebDriver driver;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
    }

    private By dragEl1 = By.xpath("//span[@id='manual1']");
    private By targetDragEl1 = By.xpath("//div[@id='target-manual1']");

    Actions actions = new Actions(driver);

    public DragAndDropPage DragAndDropMove(String manual1, String targetDragEl1) {

        actions.pause(Duration.ofSeconds(5))
                .dragAndDrop(driver.findElement(By.id("//span[@id=' " + manual1 + " ']")), driver.findElement(By.id("//div[@id='" + targetDragEl1 + " ']")))
                .build().perform();

        return this;
    }

}


