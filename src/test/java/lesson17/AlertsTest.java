package lesson17;

import Lesson15.BaseTest;
import components.AndersenUrls;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.ActionsPage;
import pages.DragAndDropPage;
import pages.SignInPage;

public class AlertsTest extends BaseTest {

    @Test
    public void alertsTest(){
        WebDriver driver = getDriver();

        String actualCongratulationsMessage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverMenu()
                .<ActionsPage>clickOption("Drag & Drop")


    }
}
