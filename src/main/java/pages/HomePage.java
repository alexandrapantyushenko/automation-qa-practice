package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailElement(String email) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + email + "')]"));
    }

    public WebElement getSignOutButton() {
        return driver.findElement(By.xpath("//div[contains(text(),'Sign Out')]"));
    }

    public HomePage clickUploadButton() {
        WebElement uploadButton = driver.findElement(By.xpath("//img[@class='w-[95px]']"));
        uploadButton.click();
        return new HomePage(driver);
    }


}
