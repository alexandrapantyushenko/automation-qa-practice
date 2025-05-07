package Lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;

import java.time.Duration;

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
                        "emily.johnson57@example.com",
                        "EmilyPass123!",
                        "EmilyPass123!")
                .clickSubmit();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.LOGIN.getUrl()));

        assertEquals(getDriver().getCurrentUrl(), AndersenUrls.LOGIN.getUrl());
    }


    @Test
    void newRUserCanNotRegistrationWithMismatchedPassword() {

        new SignInPage(getDriver())
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .clickRegistrationBtn()
                .insertNewAccountData("Emily",
                        "Johnson",
                        "08/22/1995",
                        "emily.johnson93@example.com",
                        "EmilyPass123!",
                        "EmilyPass321!")
                .clickSubmit();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Passwords must match')]")));
        Assertions.assertEquals("Passwords must match", errorMessage.getText());
    }
}
