//package lesson16.parameters;
//
//import lesson16.BaseTestNGTest;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//import pages.SignInPage;
//import pages.HomePage;
//import components.AndersenUrls;
//
//import java.time.Duration;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;
//
//public class loginWithParametersTest extends BaseTestNGTest {
//
//    @Test
//    @Parameters({"email","password"})
//    public void loginThreeUsersTest(String email, String password) {
//        WebDriver driver = getDriver();
//
//        HomePage homePage = new SignInPage(driver)
//                .navigateTo(AndersenUrls.LOGIN.getUrl())
//                .insertCredentials(email, password)
//                .clickLogin();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlToBe(AndersenUrls.ACCOUNT.getUrl()));
//
//        assertEquals(driver.getCurrentUrl(), AndersenUrls.ACCOUNT.getUrl());
//
//        WebElement emailElement = homePage.getEmailElement(email);
//        assertEquals(emailElement.getText().trim(), email, "Displayed email does not match logged-in user");
//        assertTrue(emailElement.isDisplayed(), "Email is not displayed on the home page");
//    }
//}
