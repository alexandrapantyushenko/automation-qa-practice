package lesson17;

import Lesson15.BaseTest;
import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.SelectPage;
import pages.SignInPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectPageTest extends BaseTest {


    @Test
    public void testSelectCourseAndSubmit(){



        WebDriver driver = getDriver();

        SelectPage selectPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .hoverAndClickSelect()
                .selectCountry("USA")
                .selectLanguage("English")
                .selectType("Testing")
                .setStartDate()
                .setEndDate()
                .selectCourse("AQA Java", "AQA Python");


    }
}
