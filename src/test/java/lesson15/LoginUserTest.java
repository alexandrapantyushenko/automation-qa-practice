package lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.SignInPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUserTest extends BaseTest {

    @Test
    public void userLoginTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .loginSuccessfully();

       WebElement emailElement = homePage.getEmailElement("emily.johnson57@example.com");
        assertTrue(emailElement.isDisplayed(), "Email is not displayed on the home page");

        WebElement signOutButton = homePage.getSignOutButton();
        assertTrue(signOutButton.isDisplayed(), "Sign out button is not visible");
    }

    @Test
    public void loginUserWithNotValidCredentialsTest() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("invalid.email@example.com", "wrongPass");

        SignInPage errorPage = signInPage.loginWithInvalidCredentials(
                () -> signInPage,
                signInPage.waitForErrorMessageCondition()
        );

        assertEquals("Email or password is not valid", errorPage.getErrorMessage());
    }
}
