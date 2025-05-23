package lesson15;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;

public class SignOutUserTest extends BaseTestNGTest {

    @Test
    public void userLogOutTest() {
        WebDriver driver = getDriver();

        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .clickSignOutButton();

        Assert.assertEquals(driver.getCurrentUrl(), AndersenUrls.LOGIN.getUrl());
    }
}
