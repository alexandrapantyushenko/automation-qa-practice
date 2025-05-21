package lesson17;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DragAndDropPage;
import pages.SignInPage;

public class DragAndDropTest extends BaseTestNGTest {

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

        Assert.assertEquals(actualCongratulationsMessage, "Congratulations! Let's test for the best!", "The congratulations message is not as expected.");
    }
}
