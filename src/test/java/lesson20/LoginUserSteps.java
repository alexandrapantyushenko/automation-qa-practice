package lesson20;

import components.AndersenUrls;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginUserSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        driver = CommonSteps.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(AndersenUrls.LOGIN.getUrl());
    }

    @When("the user enters valid credentials {string} and {string}")
    public void userEntersCredentials(String email, String password) {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }

    @When("clicks the login button")
    public void userClicksLogin() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.HOME.getUrl()));
    }

    @Then("the user should be redirected to the home page")
    public void userRedirectedToHomePage() {
        assertEquals(driver.getCurrentUrl(), AndersenUrls.HOME.getUrl());
    }

    @Then("the user email {string} should be displayed on the home page")
    public void emailShouldBeDisplayed(String email) {
        WebElement emailElement = driver.findElement(By.xpath("//div[contains(text(), '" + email + "')]"));
        assertTrue(emailElement.isDisplayed(), "Email is not displayed");
    }


    @Given("user is on the registration page")
    public void userIsOnTheRegistrationPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(AndersenUrls.REGISTRATION.getUrl());
    }

    @When("user inputs registration data:")
    public void userInputsRegistrationData(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);

        fillInput("firstName", data.get("First Name"));
        fillInput("lastName", data.get("Last Name"));
        fillInput("dateOfBirth", data.get("Date of Birth"));
        fillInput("email", data.get("Email"));
        fillInput("password", data.get("Password"));
        fillInput("passwordConfirmation", data.get("Confirm Password"));
    }

    private void fillInput(String nameAttribute, String value) {
        By locator = By.xpath("//input[@name='" + nameAttribute + "']");
        WebElement input = CommonSteps.driver.findElement(locator);
        input.click();
        input.clear();
        input.sendKeys(value);
        input.sendKeys(Keys.ESCAPE);
    }

    @And("user submits the registration form")
    public void userSubmitsTheRegistrationForm() {
        CommonSteps.driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("user should be redirected to login page")
    public void userShouldBeRedirectedToLoginPage() {
        wait.until(ExpectedConditions.urlToBe("https://example.com/login")); // или AndersenUrls.LOGIN.getUrl()
        assertEquals(CommonSteps.driver.getCurrentUrl(), "https://example.com/login");
    }
}
