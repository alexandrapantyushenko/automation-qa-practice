package lesson17;

import Lesson15.BaseTest;
import components.AndersenUrls;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SignInPage;

public class SelectPageTest extends BaseTest {

    @Test
    public void testSelectCourseAndSubmit() {

        WebDriver driver = getDriver();

        String actualErrorMessage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverAndClickSelect()
                .selectCountry("USA")
                .selectLanguage("English")
                .selectType("Testing")
                .setStartDate()
                .setEndDate()
                .selectCourse("AQA Java", "AQA Python")
                .clickSearchButton()
                .getErrorMessage();

    Assertions.assertEquals("Unfortunately, we did not find any courses matching your chosen criteria.",actualErrorMessage,"The error message is not as expected.");
}
}
