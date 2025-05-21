package lesson15;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.SignInPage;

import java.time.Duration;
import java.util.List;

public class RegistrationTest extends BaseTestNGTest {

    @Test
    public void newUserRegistration() {
        new SignInPage(getDriver())
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .clickRegistrationBtn()
                .insertNewAccountData("Emily",
                        "Johnson",
                        "08/22/1995",
                        "emily.johnson75@example.com",
                        "EmilyPass123!",
                        "EmilyPass123!")
                .clickSubmit(SignInPage.class);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.LOGIN.getUrl()));

        Assert.assertEquals(getDriver().getCurrentUrl(), AndersenUrls.LOGIN.getUrl());
    }

    @Test
    public void newUserCanNotRegistrationWithMismatchedPassword() {
        new SignInPage(getDriver())
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .clickRegistrationBtn()
                .insertNewAccountData("Emily",
                        "Johnson",
                        "08/22/1995",
                        "emily.johnson93@example.com",
                        "EmilyPass123!",
                        "EmilyPass321!")
                .clickSubmit(RegistrationPage.class);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Passwords must match')]")));
        Assert.assertEquals(errorMessage.getText(), "Passwords must match");
    }

    @Test
    public void userRegistrationWithEmptyFields() {
        RegistrationPage registrationPage = new SignInPage(getDriver())
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .clickRegistrationBtn()
                .insertNewAccountData("",
                        "",
                        "",
                        "",
                        "",
                        "")
                .clickSubmit(RegistrationPage.class);

        List<String> errors = registrationPage.getAllErrorMessagesList();

        Assert.assertTrue(errors.contains("Required"));
        Assert.assertEquals(errors.size(), 6);
    }

    @Test
    public void userRegistrationWithInvalidEmail() {
        RegistrationPage registrationPage = new SignInPage(getDriver())
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .clickRegistrationBtn()
                .insertNewAccountData("Emily",
                        "Johnson",
                        "08/22/1995",
                        "emily.johnson93example.com",
                        "EmilyPass123!",
                        "EmilyPass123!")
                .clickSubmit(RegistrationPage.class);

        Assert.assertEquals(registrationPage.getErrorMessage("email"), "Invalid email address");
        Assert.assertFalse(registrationPage.getSubmitBtn().isEnabled());
    }

    @Test
    public void userRegistrationWithShortPassword() {
        RegistrationPage registrationPage = new SignInPage(getDriver())
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .clickRegistrationBtn()
                .insertNewAccountData("Emily",
                        "Johnson",
                        "08/22/1995",
                        "emily.johnson93@example.com",
                        "Emily",
                        "Emily")
                .clickSubmit(RegistrationPage.class);

        Assert.assertEquals(registrationPage.getErrorMessage("password"), "Minimum 8 characters");
        Assert.assertFalse(registrationPage.getSubmitBtn().isEnabled());
    }
}
