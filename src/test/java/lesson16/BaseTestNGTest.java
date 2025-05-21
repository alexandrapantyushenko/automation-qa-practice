package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestNGTest {

    private static final Logger logger = LogManager.getLogger(BaseTestNGTest.class);

    private static ChromeOptions options;
    private WebDriver driver;

    @BeforeClass
    @Step("Setup ChromeDriver for test class")
    public void setupClass() {
        logger.info("Setting up ChromeDriver (WebDriverManager)");
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
    }

    @BeforeMethod
    @Step("Starting browser and maximizing window")
    public void startBrowser() {
        logger.info("Starting Chrome browser");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    @Step("Closing browser")
    public void closeDriver() {
        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
        }
    }
}
