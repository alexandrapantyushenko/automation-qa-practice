package pages;

import components.AndersenUrls;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SignInPage {

    private static final Logger logger = LogManager.getLogger(SignInPage.class);

    private final WebDriverWait wait;
    private WebDriver driver;

    private By emailLocator = By.xpath("//input[@name='email']");
    private By passwordLocator = By.xpath("//input[@name='password']");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");
    private By registrationBtn = By.xpath("//span[text()='Registration']");
    private By errorMessageLocator = By.xpath("//div[@class='text-right relative']/span");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Navigate to URL: {url}")
    public SignInPage navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
        return this;
    }

    @Step("Get password input type")
    public String getPasswordInputType() {
        logger.info("Getting password input type");
        WebElement passwordInput = driver.findElement(By.name("password"));
        String type = passwordInput.getAttribute("type");
        logger.info("Password input type: {}", type);
        return type;
    }

    @Step("Insert credentials with email: {email} and password: ****")
    public SignInPage insertCredentials(String email, String password) {
        logger.info("Inserting credentials for email: {}", email);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    @Step("Click Login button")
    public HomePage clickLogin() {
        logger.info("Clicking Login button");
        driver.findElement(loginButtonLocator).click();
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.HOME.getUrl()));
        logger.info("Navigated to Home page");
        return new HomePage(driver);
    }

    @Step("Get all error messages")
    public List<String> getErrorMessagesList() {
        logger.info("Retrieving all error messages");
        List<WebElement> errorElements = driver.findElements(errorMessageLocator);
        List<String> errorsText = new ArrayList<>();

        for (WebElement el : errorElements) {
            String error = el.getText().trim();
            logger.debug("Error message found: {}", error);
            errorsText.add(error);
        }
        return errorsText;
    }

    @Step("Login with invalid credentials and wait for expected condition")
    public <T> T loginWithInvalidCredentials(Supplier<T> pageSupplier, ExpectedCondition<?> conditionToWait) {
        logger.info("Clicking Login button with invalid credentials");
        driver.findElement(loginButtonLocator).click();
        try {
            wait.until(conditionToWait);
            logger.info("Expected condition met");
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for condition: {}", e.getMessage());
        }
        return pageSupplier.get();
    }

    @Step("Click Registration button")
    public RegistrationPage clickRegistrationBtn() {
        logger.info("Clicking Registration button");
        driver.findElement(registrationBtn).click();
        return new RegistrationPage(driver);
    }

    @Step("Get error message text")
    public String getErrorMessage() {
        String message = driver.findElement(errorMessageLocator).getText();
        logger.info("Error message text: {}", message);
        return message;
    }

    @Step("Wait for visibility of error message")
    public ExpectedCondition<WebElement> waitForErrorMessageCondition() {
        return ExpectedConditions.visibilityOfElementLocated(errorMessageLocator);
    }
}
