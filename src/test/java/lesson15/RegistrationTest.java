package lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import pages.SignInPage;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest extends BaseTest {

    @Test
    void newUserRegistration() {

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

        assertEquals(getDriver().getCurrentUrl(), AndersenUrls.LOGIN.getUrl());
    }


    @Test
    void newUserCanNotRegistrationWithMismatchedPassword() {

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
        Assertions.assertEquals("Passwords must match", errorMessage.getText());
    }


    @Test
    void userRegistrationWithEmptyFields(){

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

        Assertions.assertTrue(errors.contains("Required"));
        Assertions.assertEquals(6,errors.size());

    }
}
