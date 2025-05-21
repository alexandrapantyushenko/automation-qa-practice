package lesson17;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SelectPage;
import pages.SignInPage;

public class SelectPageTest extends BaseTestNGTest {

    @Test
    public void testSelectCourseAndSubmit() {

        WebDriver driver = getDriver();

        String actualErrorMessage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverMenu()
                .<SelectPage>clickOption("Select")
                .selectCountry("USA")
                .selectLanguage("English")
                .selectType("Testing")
                .setStartDate()
                .setEndDate()
                .selectCourse("AQA Java", "AQA Python")
                .clickSearchButton()
                .getErrorMessage();

        Assert.assertEquals(actualErrorMessage,
                "Unfortunately, we did not find any courses matching your chosen criteria.",
                "The error message is not as expected.");
    }
}
