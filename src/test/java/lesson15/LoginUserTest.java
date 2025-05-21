package lesson15;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;

import java.util.List;

public class LoginUserTest extends BaseTestNGTest {

    @Test
    public void userLoginTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin();

        Assert.assertEquals(driver.getCurrentUrl(), AndersenUrls.HOME.getUrl());

        WebElement emailElement = homePage.getEmailElement("emily.johnson57@example.com");
        Assert.assertTrue(emailElement.isDisplayed(), "Email is not displayed on the home page");
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

        Assert.assertEquals(errors.size(), 2, "Expected 2 error messages: one for email and one for password");
        Assert.assertTrue(errors.stream().allMatch(e -> e.equals("Required")), "All error messages should be 'Required'");
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

        Assert.assertEquals(errorPage.getErrorMessage(), "Email or password is not valid");
    }

    @Test
    public void verifyPasswordInputIsHiddenTest() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl());

        String inputType = signInPage.getPasswordInputType();

        Assert.assertEquals(inputType, "password", "Password input should have type='password' to hide the input");
    }
}
