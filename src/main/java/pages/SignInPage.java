package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    private By registrationBtn = By.xpath("//span[text()='Registration']");

    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public SignInPage navigateTo(String url){
        driver.get(url);
        return this;
    }

    public RegistrationPage clickRegistrationBtn(){
        driver.findElement(registrationBtn).click();
        return new RegistrationPage (driver);
    }



}
