import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import pages.SignInPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest{

    @Test
    void newUserRegistration(){

       new SignInPage(getDriver())
               .navigateTo(AndersenUrls.LOGIN.getUrl())
               .clickRegistrationBtn()
               .insertNewAccountData( "Emily",
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
}
