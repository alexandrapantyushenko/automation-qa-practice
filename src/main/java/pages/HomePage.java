package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By aqaPracticeButton = By.xpath("//div[@class='my-auto']");
    private By signOutButton = By.xpath("//div[contains(text(),'Sign Out')]");
    private By emailBlock = By.xpath("//div[contains(text(),'Sign Out')]");

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

    public HomePage clickUploadButton() {
        WebElement uploadButton = driver.findElement(By.xpath("//img[@class='w-[95px]']"));
        uploadButton.click();
        return new HomePage(driver);
    }
}
