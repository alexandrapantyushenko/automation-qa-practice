
package lesson21;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AndroidApplicationTest {

    AppiumDriver driver;
    AndroidPage androidPage;


    @BeforeMethod
    public void setUp() {
        driver = new AppiumDriverInit().getDriver();
        androidPage = new AndroidPage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void testTextSwitcherNextButton() {
        androidPage.clickOnView();
        androidPage.scrollUntilTextSwitcherAndClick();

        for (int i = 1; i <= 5; i++) {
            androidPage.clickNextButton();

            By dynamicCounter = By.xpath("//android.widget.TextView[@text='" + i + "']");
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(dynamicCounter));

            String displayedText = driver.findElement(dynamicCounter).getText();
            Assert.assertEquals(displayedText, String.valueOf(i), "Counter does not match the number of clicks");
        }
    }

    @Test
    public void testCountAllButtonsInViews() {
        WebElement views = driver.findElement(MobileBy.AccessibilityId("Views"));
        views.click();
        List<WebElement> allButtons = androidPage.getAllButtons();
        System.out.println("Amount of elements: " + allButtons.size());
        Assert.assertEquals(allButtons.size(), 42);
    }


    @Test
    public void testSetTimeTo1111PM() {
        androidPage.navigateToDateWidgetsDialog();
        androidPage.navigateToChangeTimeSpinner();
        androidPage.changeDateToTomorrow();
        androidPage.setTimeTo1111PM();
        androidPage.clickOkButton();

        String displayedTime = androidPage.getDisplayedTime();
        Assert.assertTrue(displayedTime.contains("23:11") || displayedTime.contains("11:11 PM"), "The time did not update.");
    }


}