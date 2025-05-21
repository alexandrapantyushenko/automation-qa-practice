package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage {

    private static final Logger logger = LogManager.getLogger(RegistrationPage.class);

    private WebDriver driver;

    private By firstNameLocator = By.xpath("//input[@name='firstName']");
    private By lastNameLocator = By.xpath("//input[@name='lastName']");
    private By dateOfBirthLocator = By.xpath("//input[@name='dateOfBirth']");
    private By emailLocator = By.xpath("//input[@name='email']");
    private By passwordLocator = By.xpath("//input[@name='password']");
    private By passwordConfirmationLocator = By.xpath("//input[@name='passwordConfirmation']");
    private By submitBtnLocator = By.xpath("//button[@type='submit']");
    private By errorMessagesLocator = By.xpath("//div[@class='text-right relative']//span");

    private By invalidEmailLocator = By.xpath("//input[@name='email']/ancestor::label/following-sibling::div//span");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getFirstNameLocator() {
        return firstNameLocator;
    }

    public By getLastNameLocator() {
        return lastNameLocator;
    }

    @Step("Get Submit button element")
    public WebElement getSubmitBtn() {
        logger.info("Getting Submit button element");
        return driver.findElement(submitBtnLocator);
    }

    @Step("Set value '{value}' to element located by {locator}")
    private void setValue(By locator, String value) {
        logger.info("Setting value '{}' to element located by {}", value, locator);
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.ESCAPE);
    }

    @Step("Click element located by {locator}")
    private void click(By locator) {
        logger.info("Clicking element located by {}", locator);
        driver.findElement(locator).click();
    }

    @Step("Insert new account data: firstName={firstName}, lastName={lastName}, dateOfBirth={dateOfBirth}, email={email}")
    public RegistrationPage insertNewAccountData(String firstName, String lastName, String dateOfBirth, String email, String password, String passwordConfirmation) {
        logger.info("Inserting new account data");
        setValue(firstNameLocator, firstName);
        setValue(lastNameLocator, lastName);
        setValue(dateOfBirthLocator, dateOfBirth);
        setValue(emailLocator, email);
        setValue(passwordLocator, password);
        setValue(passwordConfirmationLocator, passwordConfirmation);
        return this;
    }

    @Step("Click Submit and navigate to {expectedPage}")
    public <T> T clickSubmit(Class<T> expectedPage){
        logger.info("Clicking Submit button, expecting page: {}", expectedPage.getSimpleName());
        click(submitBtnLocator);
        try {
            if(expectedPage.isInstance(this)){
                return expectedPage.cast(this);
            }
            return expectedPage.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e){
            logger.error("Error creating page instance: {}", expectedPage.getSimpleName(), e);
            throw new RuntimeException("Error creating page: " + expectedPage.getSimpleName(), e);
        }
    }

    @Step("Get all error messages list")
    public List<String> getAllErrorMessagesList(){
        logger.info("Getting all error messages");
        List<WebElement> errorElements = driver.findElements(errorMessagesLocator);
        List<String> errorsText = new ArrayList<>();

        for(WebElement el : errorElements){
            String text = el.getText().trim();
            logger.debug("Found error message: '{}'", text);
            errorsText.add(text);
        }
        return errorsText;
    }

    @Step("Get error message for field: {fieldName}")
    public String getErrorMessage(String fieldName) {
        String xpath = "//input[@name='" + fieldName + "']/ancestor::label/following-sibling::div//span";
        logger.info("Getting error message for field '{}'", fieldName);
        String message = driver.findElement(By.xpath(xpath)).getText();
        logger.debug("Error message for field '{}': '{}'", fieldName, message);
        return message;
    }
}
