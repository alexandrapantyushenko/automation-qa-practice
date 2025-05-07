package Lesson15;

import components.AndersenUrls;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class ElementTest extends BaseTest {

    @Test
    public void compareFirstNameAndLastName() {
        WebDriver driver = getDriver();
        driver.get(AndersenUrls.REGISTRATION.getUrl());
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebElement firstNameElement = driver.findElement(registrationPage.getFirstNameLocator());
        WebElement lastNameElement = driver.findElement(registrationPage.getLastNameLocator());
        Assertions.assertEquals(firstNameElement, lastNameElement);
    }
}
