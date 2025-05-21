package lesson15;

import components.AndersenUrls;
import lesson16.BaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.ElementUtils;

public class ElementTest extends BaseTestNGTest {

    @Test
    public void compareFirstNameAndLastName() {
        WebDriver driver = getDriver();
        driver.get(AndersenUrls.REGISTRATION.getUrl());

        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebElement firstNameElement = driver.findElement(registrationPage.getFirstNameLocator());
        WebElement lastNameElement = driver.findElement(registrationPage.getLastNameLocator());

        ElementUtils.compareElementsPositionAndSize(firstNameElement, lastNameElement);
    }
}
