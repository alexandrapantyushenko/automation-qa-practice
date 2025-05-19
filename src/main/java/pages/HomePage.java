package pages;

import components.AndersenUrls;
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

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By aqaPracticeButton = By.xpath("//div[@class='my-auto']");
    private By signOutButton = By.xpath("//div[contains(text(),'Sign Out')]");
    private By emailBlock = By.xpath("//div[contains(text(),'Sign Out')]");

    private By editBtnLocator = By.xpath("//img[@alt='Edit']");
    private By firstNameLocator = By.xpath("//input[@name='firstName']");
    private By lastNameLocator = By.xpath("//input[@name='lastName']");
    private By saveButton = By.xpath("//button[@type='submit']");

    public HomePage refresh() {
        driver.navigate().refresh();
        return this;
    }

    public HomePage clickSaveButton() {
        driver.findElement(saveButton).click();
        return this;
    }

   public HomePage updateProfileFields(Map<String, String> fieldValues) {
        for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
            WebElement input = driver.findElement(By.name(entry.getKey()));
            input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            input.sendKeys(Keys.DELETE);
            input.sendKeys(entry.getValue());
        }
        return this;
    }

    public Map<String, String> readProfileFields(List<String> fieldNames) {
        Map<String, String> result = new HashMap<>();
        for (String fieldName : fieldNames) {
            WebElement input = driver.findElement(By.name(fieldName));
            result.put(fieldName, input.getAttribute("value"));
        }
        return result;
    }


    public String getFieldValue(String fieldName) {
        return driver.findElement(By.name(fieldName)).getAttribute("value");
    }

    public HomePage clickEditButton() {
        WebElement editButton = driver.findElement(editBtnLocator);
        editButton.click();
        return new HomePage(driver);
    }

    public HomePage hoverMenu() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(aqaPracticeButton));

        Actions actions = new Actions(driver);
        actions.moveToElement(menuButton)
                .pause(Duration.ofSeconds(2))
                .perform();

        return this;
    }

    public <T> T clickOption(String menuOption) {
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

    public WebElement getEmailElement(String email) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + email + "')]"));
    }

    public WebElement getSignOutButton() {
        return driver.findElement(signOutButton);
    }

    public SignInPage clickSignOutButton() {
        driver.findElement(signOutButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(AndersenUrls.LOGIN.getUrl()));

        return new SignInPage(driver);
    }

    public HomePage clickUploadButton() {
        WebElement uploadButton = driver.findElement(By.xpath("//img[@class='w-[95px]']"));
        uploadButton.click();
        return new HomePage(driver);
    }
}
