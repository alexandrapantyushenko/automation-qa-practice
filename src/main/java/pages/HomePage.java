package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By aqaPracticeButton = By.xpath("//div[text()='AQA Practice']/..");
    private By selectOption = By.xpath("//div[text()='Select']");

    private By signOutButton = By.xpath("//div[contains(text(),'Sign Out')]");
    private By emailBlock = By.xpath("//div[contains(text(),'Sign Out')]");


    public SelectPage hoverAndClickSelect(){
        Actions actions = new Actions(driver);

        WebElement menu = driver.findElement(aqaPracticeButton);
        WebElement select = driver.findElement(selectOption);

        actions.moveToElement(menu)
                .pause(Duration.ofMillis(500))
                .click(select)
                .build()
                .perform();

        return new SelectPage(driver);
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
