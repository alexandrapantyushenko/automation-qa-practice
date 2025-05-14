package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsPage {

    private final WebDriverWait wait;
    private WebDriver driver;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By confirmButton = By.xpath("//button[@id='AlertButton']");
    private By getDiscountButton = By.xpath("//button[contains (text(), 'Get Discount')]");
    private By cancelCourseButton = By.xpath("//button[@data-test-id='PromptButton']");
    private By resultsMessage = By.xpath("//span[contains(text(), 'Results')]/following-sibling::span");
    private By iframeLocator = By.xpath("//iframe[@title='Finish your registration']");

    public ActionsPage switchToIframe() {
        WebElement iframeElement = driver.findElement(iframeLocator);
        driver.switchTo().frame(iframeElement);
        return this;
    }

    public ActionsPage handleFirstAlert() {

        WebElement confirmButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        confirmButtonElement.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if (alertText.equals("You have called alert!")) {
            alert.accept();
        } else {
            throw new RuntimeException("First alert text did't match");
        }
        return this;
    }

    public ActionsPage handleSecondAlert() {

        WebElement discountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(getDiscountButton));
        wait.until(ExpectedConditions.elementToBeClickable(discountButton)).click();


        Actions action = new Actions(driver);
        action.doubleClick(discountButton).perform();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (alertText.equals("Are you sure you want to apply the discount?")) {
            alert.accept();
        } else {
            throw new RuntimeException("Second alert text didn't match!");
        }
        return this;
    }

    public ActionsPage handleThirdAlertWithInput(String inputText) {

        WebElement cancelCourseButtonElement = driver.findElement(cancelCourseButton);

        Actions action = new Actions(driver);
        action.contextClick(cancelCourseButtonElement).perform();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if (alertText.equals("Here you may describe a reason why you are cancelling your registration (or leave this field empty).")) {
            alert.sendKeys(inputText);
            alert.accept();
        } else {
            throw new RuntimeException("Third alert text didn't match!");
        }
        return this;
    }

    public String getResultMessage() {
        WebElement resultMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsMessage));
        String message = resultMessageElement.getText();
        return message;
    }

}
