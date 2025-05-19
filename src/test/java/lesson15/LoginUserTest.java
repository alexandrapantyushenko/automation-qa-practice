package lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.SignInPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUserTest extends BaseTest {

    @Test
    public void userLoginTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin();

        assertEquals(driver.getCurrentUrl(), AndersenUrls.HOME.getUrl());

        WebElement emailElement = homePage.getEmailElement("emily.johnson57@example.com");
        assertTrue(emailElement.isDisplayed(), "Email is not displayed on the home page");

    }

    @Test
    public void loginWithEmptyFieldsTest() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("", "");

        SignInPage errorPage = signInPage.loginWithInvalidCredentials(
                () -> signInPage,
                signInPage.waitForErrorMessageCondition()
        );

        List<String> errors = errorPage.getErrorMessagesList();

        assertEquals(2, errors.size(), "Expected 2 error messages: one for email and one for password");
        assertTrue(errors.stream().allMatch(e -> e.equals("Required")), "All error messages should be 'Required'");

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
