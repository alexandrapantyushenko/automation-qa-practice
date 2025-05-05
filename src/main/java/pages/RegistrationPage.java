package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {

    private WebDriver driver;

    public By getFirstNameLocator() {
        return firstNameLocator;
    }

    public By getLastNameLocator() {
        return lastNameLocator;
    }

    private By firstNameLocator = By.xpath("//input[@name='firstName']");
    private By lastNameLocator = By.xpath("//input[@name='lastName']");
    private By dateOfBirthLocator = By.xpath("//input[@name='dateOfBirth']");
    private By emailLocator = By.xpath("//input[@name='email']");
    private By passwordLocator = By.xpath("//input[@name='password']");
    private By passwordConfirmationLocator = By.xpath("//input[@name='passwordConfirmation']");
    private By submitBtnLocator = By.xpath("//button[@type='submit']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private void setValue(By locator, String value) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.ESCAPE);
    }

    private void click(By locator){
        driver.findElement(locator).click();
    }

       public RegistrationPage insertNewAccountData(String firstName, String lastName, String dateOfBirth, String email, String password, String passwordConfirmation) {
        setValue(firstNameLocator, firstName);
        setValue(lastNameLocator, lastName);
        setValue(dateOfBirthLocator, dateOfBirth);
        setValue(emailLocator, email);
        setValue(passwordLocator, password);
        setValue(passwordConfirmationLocator, passwordConfirmation);

        return this;
    }

    public SignInPage clickSubmit(){
        click(submitBtnLocator);
        return new SignInPage(driver);
    }
}
