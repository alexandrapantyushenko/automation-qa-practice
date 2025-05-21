package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsPage {

    private static final Logger logger = LogManager.getLogger(ActionsPage.class);

    private final WebDriverWait wait;
    private WebDriver driver;

    private By confirmButton = By.xpath("//button[@id='AlertButton']");
    private By getDiscountButton = By.xpath("//button[contains (text(), 'Get Discount')]");
    private By cancelCourseButton = By.xpath("//button[@data-test-id='PromptButton']");
    private By resultsMessage = By.xpath("//span[contains(text(), 'Results')]/following-sibling::span");
    private By iframeLocator = By.xpath("//iframe[@title='Finish your registration']");

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Switching to iframe")
    public ActionsPage switchToIframe() {
        logger.info("Switching to iframe");
        WebElement iframeElement = driver.findElement(iframeLocator);
        driver.switchTo().frame(iframeElement);
        return this;
    }

    @Step("Handling first alert")
    public ActionsPage handleFirstAlert() {
        logger.info("Waiting for and clicking confirm button for first alert");
        WebElement confirmButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        confirmButtonElement.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        logger.info("First alert text: {}", alertText);

        if (alertText.equals("You have called alert!")) {
            alert.accept();
            logger.info("First alert accepted");
        } else {
            String error = "First alert text didn't match";
            logger.error(error);
            throw new RuntimeException(error);
        }
        return this;
    }

    @Step("Handling second alert")
    public ActionsPage handleSecondAlert() {
        logger.info("Waiting for discount button and clicking");
        WebElement discountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(getDiscountButton));
        wait.until(ExpectedConditions.elementToBeClickable(discountButton)).click();

        logger.info("Performing double click on discount button");
        Actions action = new Actions(driver);
        action.doubleClick(discountButton).perform();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        logger.info("Second alert text: {}", alertText);

        if (alertText.equals("Are you sure you want to apply the discount?")) {
            alert.accept();
            logger.info("Second alert accepted");
        } else {
            String error = "Second alert text didn't match!";
            logger.error(error);
            throw new RuntimeException(error);
        }
        return this;
    }

    @Step("Handling third alert with input: {inputText}")
    public ActionsPage handleThirdAlertWithInput(String inputText) {
        logger.info("Performing context click on cancel course button");
        WebElement cancelCourseButtonElement = driver.findElement(cancelCourseButton);

        Actions action = new Actions(driver);
        action.contextClick(cancelCourseButtonElement).perform();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        logger.info("Third alert text: {}", alertText);

        if (alertText.equals("Here you may describe a reason why you are cancelling your registration (or leave this field empty).")) {
            alert.sendKeys(inputText);
            alert.accept();
            logger.info("Third alert accepted with input");
        } else {
            String error = "Third alert text didn't match!";
            logger.error(error);
            throw new RuntimeException(error);
        }
        return this;
    }

    @Step("Getting result message")
    public String getResultMessage() {
        logger.info("Waiting for result message");
        WebElement resultMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsMessage));
        String message = resultMessageElement.getText();
        logger.info("Result message received: {}", message);
        return message;
    }

}
