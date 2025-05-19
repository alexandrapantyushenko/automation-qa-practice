package lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SignInPage;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditUserInfoTest extends BaseTest {

    @Test
    public void checkUserInfoTest() {
        WebDriver driver = getDriver();

        HomePage homePage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin()
                .clickEditButton();

        assertEquals(homePage.getFieldValue("firstName"), "Emily", "First name is incorrect");
        assertEquals(homePage.getFieldValue("lastName"), "Johnson", "Last name is incorrect");
        assertEquals(homePage.getFieldValue("email"), "emily.johnson89@example.com", "Email is incorrect");
        assertEquals(homePage.getFieldValue("dateOfBirth"), "22/08/1995", "Date of Birth is incorrect");
    }

    @Test
    public void updateUserProfileWithMapAndVerify() {
        WebDriver driver = getDriver();

        HomePage homePage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl())
                .insertCredentials("emily.johnson89@example.com", "EmilyPass123!")
                .clickLogin()
                .clickEditButton();

        Map<String, String> newData = Map.of(
                "firstName", "Emma",
                "lastName", "Smith",
                "dateOfBirth", "15/09/1993"
        );

        homePage.updateProfileFields(newData)
                .clickSaveButton()
                .clickEditButton();

        Map<String, String> actualData = homePage.readProfileFields(List.of("firstName", "lastName", "dateOfBirth"));

        for (String key : newData.keySet()) {
            assertEquals(actualData.get(key), newData.get(key), key + " was not updated correctly");
        }
    }
}








