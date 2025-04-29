import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    private static ChromeOptions options;
    private WebDriver driver;

    @BeforeAll
    static void downloadDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeEach
    void startBrowser() {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

//    @AfterEach
//    void closeDriver() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
