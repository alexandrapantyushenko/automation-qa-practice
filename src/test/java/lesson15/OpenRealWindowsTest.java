package lesson15;

import components.Urls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;

public class OpenRealWindowsTest extends BaseTest {

    @Test
    void openPagesAndHandleZoo() {
        WebDriver driver = getDriver();

        driver.get(Urls.PAGE1.getUrl());

        for (int i = 1; i < Urls.values().length; i++) {
            driver.switchTo().newWindow(WindowType.WINDOW);
            driver.get(Urls.values()[i].getUrl());
            driver.manage().window().maximize();
        }

        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        for (String window : windowHandles) {
            driver.switchTo().window(window);

            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();

            System.out.println("Title: " + title);
            System.out.println("URL: " + currentUrl);

            if (title.toLowerCase().contains("zoo")) {
                System.out.println("Closing window with 'Zoo' in the title...");
                driver.close();
            }
        }

        ArrayList<String> remainingWindows = new ArrayList<>(driver.getWindowHandles());
        if (!remainingWindows.isEmpty()) {
            driver.switchTo().window(remainingWindows.get(0));
        }
    }
}
