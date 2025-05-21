package lesson15;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;

import java.io.File;
import java.time.Duration;

public class UpdatePhotoTest extends BaseTestNGTest {

    @Test
    public void updateUserPhoto() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl());

        signInPage.insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.HOME.getUrl()));

        HomePage homePage = new HomePage(driver);

        homePage.clickUploadButton();

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));

        File fileToUpload = new File("C:/Users/User/Pictures/unnamed.gif");
        fileInput.sendKeys(fileToUpload.getAbsolutePath());

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Your photo has been updated']")));
        Assert.assertEquals(modal.getText(), "Your photo has been updated");

        WebElement closeModalButton = modal.findElement(By.xpath("//img[@alt='Close']"));
        closeModalButton.click();
    }
}
