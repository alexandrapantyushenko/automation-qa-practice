package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class DragAndDropPage {

    private WebDriver driver;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
    }

    private By finishBtn = By.xpath("//button[@id='DragNDropPageFinishButton']");
    private By dragAndDropMessage = By.xpath("//section[@class='relative']/div");

    Actions actions;

    public DragAndDropPage dragAndDropMove(String manualElementId, String targetElementId) {
        actions = new Actions(driver);


        By dragEl = By.xpath("//span[@id='" + manualElementId + "']");
        By targetDragEl = By.xpath("//div[@id='" + targetElementId + "']");


        WebElement source = driver.findElement(dragEl);
        WebElement target = driver.findElement(targetDragEl);


        actions.pause(Duration.ofSeconds(2))
                .dragAndDrop(source, target)
                .build()
                .perform();

        return this;
    }

    public DragAndDropPage clickFinishBtn() {
        driver.findElement(finishBtn).click();
        return this;
    }

    public String getDragAndDropMessage() {
        return driver.findElement(dragAndDropMessage).getText();
    }
}
