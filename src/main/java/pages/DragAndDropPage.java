package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DragAndDropPage {

    private static final Logger logger = LogManager.getLogger(DragAndDropPage.class);

    private WebDriver driver;

    private By finishBtn = By.xpath("//button[@id='DragNDropPageFinishButton']");
    private By dragAndDropMessage = By.xpath("//section[@class='relative']/div");

    private Actions actions;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    @Step("Perform drag and drop from element '{manualElementId}' to element '{targetElementId}'")
    public DragAndDropPage dragAndDropMove(String manualElementId, String targetElementId) {
        logger.info("Dragging element '{}' to element '{}'", manualElementId, targetElementId);

        By dragEl = By.xpath("//span[@id='" + manualElementId + "']");
        By targetDragEl = By.xpath("//div[@id='" + targetElementId + "']");

        WebElement source = driver.findElement(dragEl);
        WebElement target = driver.findElement(targetDragEl);

        actions.pause(Duration.ofSeconds(2))
                .dragAndDrop(source, target)
                .build()
                .perform();

        logger.info("Drag and drop action performed");
        return this;
    }

    @Step("Click finish button")
    public DragAndDropPage clickFinishBtn() {
        logger.info("Clicking finish button");
        driver.findElement(finishBtn).click();
        return this;
    }

    @Step("Get drag and drop message")
    public String getDragAndDropMessage() {
        String message = driver.findElement(dragAndDropMessage).getText();
        logger.info("Drag and drop message: {}", message);
        return message;
    }
}
