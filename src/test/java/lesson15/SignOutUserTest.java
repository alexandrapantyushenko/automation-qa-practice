package lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SignInPage;

public class SignOutUserTest extends BaseTest{

    @Test
    public void userLogOutTest() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .clickSignOutButton();

        Assertions.assertEquals(AndersenUrls.LOGIN.getUrl(), driver.getCurrentUrl());

    }
}
