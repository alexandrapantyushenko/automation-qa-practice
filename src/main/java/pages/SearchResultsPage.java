package pages;

import components.AndersenUrls;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {

    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class);

    private WebDriver driver;

    private By errorMessage = By.xpath("//h2");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get error message on search results page")
    public String getErrorMessage() {
        logger.info("Waiting for URL to contain {}", AndersenUrls.SEARCH_RESULTS.getUrl());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(AndersenUrls.SEARCH_RESULTS.getUrl()));

        String errorText = driver.findElement(errorMessage).getText();
        logger.info("Error message found: {}", errorText);
        return errorText;
    }
}
