package pages;

import components.AndersenUrls;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SignInPage {

    private final WebDriverWait wait;
    private WebDriver driver;

    private By emailLocator = By.xpath("//input[@name='email']");
    private By passwordLocator = By.xpath("//input[@name='password']");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");
    private By registrationBtn = By.xpath("//span[text()='Registration']");
    private By errorMessageLocator = By.xpath("//div[@class='text-right relative']/span");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public SignInPage navigateTo(String url) {
        driver.get(url);
        return this;
    }

    public String getPasswordInputType() {
        WebElement passwordInput = driver.findElement(By.name("password"));
        return passwordInput.getAttribute("type");
    }

    public SignInPage insertCredentials(String email, String password) {
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePage clickLogin(){
        driver.findElement(loginButtonLocator).click();
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.HOME.getUrl()));
        return new HomePage(driver);
    }

    public List<String> getErrorMessagesList(){

        List<WebElement> errorElements = driver.findElements(errorMessageLocator);
        List<String> errorsText = new ArrayList<>();

        for(WebElement el : errorElements){
            errorsText.add(el.getText().trim());
        }
        return errorsText;
    }

    public <T> T loginWithInvalidCredentials(Supplier<T> pageSupplier, ExpectedCondition<?> conditionToWait) {
        driver.findElement(loginButtonLocator).click();
        try {
            wait.until(conditionToWait);
        } catch (TimeoutException e) {
            System.err.println("Expected condition was not met in time: " + e.getMessage());
        }
        return pageSupplier.get();
    }

    public RegistrationPage clickRegistrationBtn() {
        driver.findElement(registrationBtn).click();
        return new RegistrationPage(driver);
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessageLocator).getText();
    }

    public ExpectedCondition<WebElement> waitForErrorMessageCondition(){
        return ExpectedConditions.visibilityOfElementLocated(errorMessageLocator);
    }
}


