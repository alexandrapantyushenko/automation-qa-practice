package pages;

import components.AndersenUrls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {

    private WebDriver driver;

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
    }

    private By errorMessage = By.xpath("//h2");

    public String getErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(AndersenUrls.SEARCH_RESULTS.getUrl()));
        return driver.findElement(errorMessage).getText();
    }
}
