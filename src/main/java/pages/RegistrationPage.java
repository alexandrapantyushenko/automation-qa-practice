package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
    private By errorMessagesLocator = By.xpath("//div[@class='text-right relative']//span");

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

    private void click(By locator) {
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

    public <T> T clickSubmit(Class<T> expectedPage){
        click(submitBtnLocator);
        try{
            if(expectedPage.isInstance(this)){
                return expectedPage.cast(this);
            }
            return expectedPage.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e){
            throw new RuntimeException("Ошибка создания страницы: " + expectedPage.getSimpleName(), e);
        }
   }


    public List<String> getAllErrorMessagesList(){

        List<WebElement> errorElements = driver.findElements(errorMessagesLocator);
        List<String> errorsText = new ArrayList<>();

        for(WebElement el : errorElements){
            errorsText.add(el.getText().trim());
        }
        return errorsText;
    }
}
