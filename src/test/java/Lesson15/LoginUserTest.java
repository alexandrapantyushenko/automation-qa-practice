package Lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;
import pages.HomePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUserTest extends BaseTest {

    @Test
    public void userLoginTest() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl());

        signInPage.insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.ACCOUNT.getUrl()));

        assertEquals(driver.getCurrentUrl(), AndersenUrls.ACCOUNT.getUrl());

        HomePage homePage = new HomePage(driver);
        WebElement emailElement = homePage.getEmailElement("emily.johnson57@example.com");
        assertTrue(emailElement.isDisplayed(), "Email is not displayed on the home page");

        WebElement signOutButton = homePage.getSignOutButton();
        assertTrue(signOutButton.isDisplayed(), "Sign out button is not visible");
    }

    @Test
    public void loginUserWithNotValidCredentials(){
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage()
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57example.com", "EmilyPass321")
                .


    }
}
