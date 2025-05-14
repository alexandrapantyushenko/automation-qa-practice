package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    private By emailLocator = By.xpath("//input[@name='email']");
    private By passwordLocator = By.xpath("//input[@name='password']");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");
    private By registrationBtn = By.xpath("//span[text()='Registration']");
    private By errorMessage = By.xpath("//div[@class='text-right relative']/span");

    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public SignInPage navigateTo(String url) {
        driver.get(url);
        return this;
    }

    public SignInPage insertCredentials(String email, String password) {
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePage clickLogin() {
        driver.findElement(loginButtonLocator).click();
        return new HomePage(driver);
    }

    public RegistrationPage clickRegistrationBtn() {
        driver.findElement(registrationBtn).click();
        return new RegistrationPage(driver);
    }


}


