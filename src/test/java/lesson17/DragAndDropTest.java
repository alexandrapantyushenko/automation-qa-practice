package lesson17;

import Lesson15.BaseTest;
import components.AndersenUrls;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.DragAndDropPage;
import pages.SignInPage;

public class DragAndDropTest extends BaseTest {

    @Test
    public void dragAndDropTest() {

        WebDriver driver = getDriver();

        String actualCongratulationsMessage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverMenu()
                .<DragAndDropPage>clickOption("Drag & Drop")
                .dragAndDropMove("manual1", "target-manual1")
                .dragAndDropMove("manual2", "target-manual2")
                .dragAndDropMove("auto1", "target-auto1")
                .dragAndDropMove("auto2", "target-auto2")
                .getDragAndDropMessage();

        Assertions.assertEquals("Congratulations! Let's test for the best!", actualCongratulationsMessage, "The congratulations message is not as expected.");


    }
}
