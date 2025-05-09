package lesson17;

import Lesson15.BaseTest;
import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SignInPage;

public class DragAndDropTest extends BaseTest {

    @Test
    public void dragAndDropTest(){

        WebDriver driver = getDriver();

        String actualErrorMessage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverMenu()
                .clickOption("Drag & Drop")

    }
}
