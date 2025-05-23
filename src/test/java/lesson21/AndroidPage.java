package lesson21;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class AndroidPage {
    AppiumDriver driver;
    WebDriverWait wait;

    public AndroidPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final class Locators {
        private static final By views = AppiumBy.accessibilityId("Views");
        private static final By timer = By.xpath("//android.widget.TextView[4]");
        private static final By textSwitcherNextButton = AppiumBy.accessibilityId("Next");
        private static final By textSwitcherCounter = AppiumBy.id("android:id/text1");
    }

    public void scrollUntilTextSwitcherAndClick() {
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));
        element.click();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.textSwitcherNextButton)).click();
    }

    public void clickOnView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
    }

    public List<WebElement> getAllButtons() {
        List<WebElement> allButtons = new ArrayList<>();
        Set<String> texts = new HashSet<>();

        boolean canScrollMore = true;
        int previousCount = 0;

        while (canScrollMore) {
            List<WebElement> visibleButtons = driver.findElements(MobileBy.xpath(
                    "//android.widget.ListView[@resource-id='android:id/list']/android.widget.TextView[@resource-id='android:id/text1']"));

            for (WebElement btn : visibleButtons) {
                String text = btn.getText();
                if (!texts.contains(text) && btn.isDisplayed() && !text.trim().isEmpty()) {
                    allButtons.add(btn);
                    texts.add(text);
                }
            }

            if (texts.size() > previousCount) {
                previousCount = texts.size();

                driver.findElement(MobileBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().resourceId(\"android:id/list\")).scrollForward()"));
            } else {

                canScrollMore = false;
            }
        }

        return allButtons;
    }

    public void navigateToDateWidgetsDialog() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
        driver.findElement(AppiumBy.accessibilityId("Date Widgets")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Dialog")).click();
    }

    public void navigateToChangeTimeSpinner() {
        driver.findElement(AppiumBy.accessibilityId("change the time (spinner)")).click();
    }

    public void changeDateToTomorrow() {
        driver.findElement(AppiumBy.accessibilityId("change the date")).click();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        String formattedDate = tomorrow.format(formatter);
        driver.findElement(By.xpath("//android.view.View[@content-desc='" + formattedDate + "']")).click();
        driver.findElement(By.id("android:id/button1")).click();
    }

    public void setTimeTo1111PM() {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().className(\"android.widget.NumberPicker\").instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"11\"))"
        ));

        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().className(\"android.widget.NumberPicker\").instance(1))" +
                        ".scrollIntoView(new UiSelector().text(\"11\"))"
        ));

        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().className(\"android.widget.NumberPicker\").instance(2))" +
                        ".scrollIntoView(new UiSelector().text(\"PM\"))"
        ));

        driver.findElement(MobileBy.id("android:id/button1")).click();
    }

    public void clickOkButton() {
        driver.findElement(MobileBy.id("android:id/button1")).click();
    }

    public String getDisplayedDate() {
        return driver.findElement(By.id("io.appium.android.apis:id/dateDisplay")).getText();
    }

    public String getDisplayedTime() {
        String dateTime = getDisplayedDate();
        return dateTime.split(" ")[1];
    }

}