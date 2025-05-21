package pages;

import components.AndersenUrls;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    private WebDriver driver;

    private By aqaPracticeButton = By.xpath("//div[@class='my-auto']");
    private By signOutButton = By.xpath("//div[contains(text(),'Sign Out')]");
    private By emailBlock = By.xpath("//div[contains(text(),'Sign Out')]");

    private By editBtnLocator = By.xpath("//img[@alt='Edit']");
    private By firstNameLocator = By.xpath("//input[@name='firstName']");
    private By lastNameLocator = By.xpath("//input[@name='lastName']");
    private By saveButton = By.xpath("//button[@type='submit']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Save button")
    public HomePage clickSaveButton() {
        logger.info("Clicking Save button");
        driver.findElement(saveButton).click();
        return this;
    }

    @Step("Update profile fields: {fieldValues}")
    public HomePage updateProfileFields(Map<String, String> fieldValues) {
        logger.info("Updating profile fields: {}", fieldValues);
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            WebElement input = driver.findElement(By.name(entry.getKey()));
            input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            input.sendKeys(Keys.DELETE);
            input.sendKeys(entry.getValue());
            logger.debug("Updated field '{}' with value '{}'", entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Step("Read profile fields: {fieldNames}")
    public Map<String, String> readProfileFields(List<String> fieldNames) {
        logger.info("Reading profile fields: {}", fieldNames);
        Map<String, String> result = new HashMap<>();
        for (String fieldName : fieldNames) {
            WebElement input = driver.findElement(By.name(fieldName));
            String value = input.getAttribute("value");
            logger.debug("Field '{}' value: '{}'", fieldName, value);
            result.put(fieldName, value);
        }
        return result;
    }

    @Step("Get field value for: {fieldName}")
    public String getFieldValue(String fieldName) {
        String value = driver.findElement(By.name(fieldName)).getAttribute("value");
        logger.info("Value of field '{}': '{}'", fieldName, value);
        return value;
    }

    @Step("Click Edit button")
    public HomePage clickEditButton() {
        logger.info("Clicking Edit button");
        WebElement editButton = driver.findElement(editBtnLocator);
        editButton.click();
        return new HomePage(driver);
    }

    @Step("Hover over AQA Practice menu")
    public HomePage hoverMenu() {
        logger.info("Hovering over AQA Practice menu");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(aqaPracticeButton));

        Actions actions = new Actions(driver);
        actions.moveToElement(menuButton)
                .pause(Duration.ofSeconds(2))
                .perform();

        return this;
    }

    @Step("Click menu option: {menuOption}")
    @SuppressWarnings("unchecked")
    public <T> T clickOption(String menuOption) {
        logger.info("Clicking menu option: {}", menuOption);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement selectOptionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + menuOption + "']")));
        selectOptionButton.click();

        switch (menuOption) {
            case "Select":
                return (T) new SelectPage(driver);
            case "Drag & Drop":
                return (T) new DragAndDropPage(driver);
            case "Actions, Alerts & Iframes":
                return (T) new ActionsPage(driver);
            default:
                throw new IllegalArgumentException("Invalid menu option: " + menuOption);
        }
    }

    @Step("Get email element by email: {email}")
    public WebElement getEmailElement(String email) {
        logger.info("Getting email element for email: {}", email);
        return driver.findElement(By.xpath("//div[contains(text(), '" + email + "')]"));
    }

    @Step("Get Sign Out button element")
    public WebElement getSignOutButton() {
        logger.info("Getting Sign Out button element");
        return driver.findElement(signOutButton);
    }

    @Step("Click Sign Out button")
    public SignInPage clickSignOutButton() {
        logger.info("Clicking Sign Out button");
        driver.findElement(signOutButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(AndersenUrls.LOGIN.getUrl()));

        return new SignInPage(driver);
    }

    @Step("Click Upload button")
    public HomePage clickUploadButton() {
        logger.info("Clicking Upload button");
        WebElement uploadButton = driver.findElement(By.xpath("//img[@class='w-[95px]']"));
        uploadButton.click();
        return new HomePage(driver);
    }
}
