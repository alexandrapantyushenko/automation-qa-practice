package lesson17;

import lesson15.BaseTest;
import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import pages.ActionsPage;
import pages.SignInPage;

public class AlertsTest extends BaseTest {

    @Test
    public void alertsTest() {
        WebDriver driver = getDriver();

        ActionsPage actionsPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverMenu()
                .<ActionsPage>clickOption("Actions, Alerts & Iframes")
                .switchToIframe();

        actionsPage.handleFirstAlert();
        Assert.assertEquals(actionsPage.getResultMessage(), "Congratulations, you have successfully enrolled in the course!");

        actionsPage.handleSecondAlert();
        Assert.assertEquals(actionsPage.getResultMessage(), "You received a 10% discount on the second course.");

        actionsPage.handleThirdAlertWithInput("Test");
        Assert.assertEquals(actionsPage.getResultMessage(), "Your course application has been cancelled. Reason: Test");

    }
}
