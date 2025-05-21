package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SelectPage {

    private static final Logger logger = LogManager.getLogger(SelectPage.class);

    private WebDriver driver;

    private By countrySelect = By.xpath("//select[@title='Select country']");
    private By languageSelect = By.xpath("//select[@title='Select language']");
    private By typeSelect = By.xpath("//select[@title='Select type']");
    private By startDateInput = By.xpath("//input[@title = 'Start date']");
    private By endDateInput = By.xpath("//input[@title = 'End date']");
    private By coursesSelect = By.xpath("//select[@id='MultipleSelect']");
    private By searchButton = By.xpath("//button[@type='submit']");

    public SelectPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Select country: {country}")
    public SelectPage selectCountry(String country) {
        logger.info("Selecting country: {}", country);
        WebElement countryDropdown = driver.findElement(countrySelect);
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(country);
        return this;
    }

    @Step("Select language: {language}")
    public SelectPage selectLanguage(String language) {
        logger.info("Selecting language: {}", language);
        WebElement languageDropdown = driver.findElement(languageSelect);
        Select select = new Select(languageDropdown);
        select.selectByVisibleText(language);
        return this;
    }

    @Step("Select type: {type}")
    public SelectPage selectType(String type) {
        logger.info("Selecting type: {}", type);
        WebElement typeDropdown = driver.findElement(typeSelect);
        Select select = new Select(typeDropdown);
        select.selectByVisibleText(type);
        return this;
    }

    @Step("Set start date to next Monday: calculated date")
    public SelectPage setStartDate() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(DayOfWeek.MONDAY).plusWeeks(1);
        String startDate = nextMonday.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        logger.info("Setting start date to {}", startDate);
        WebElement startDayField = driver.findElement(startDateInput);
        startDayField.sendKeys(startDate);
        return this;
    }

    @Step("Set end date two weeks after next Monday")
    public SelectPage setEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(DayOfWeek.MONDAY).plusWeeks(1);
        LocalDate endDate = nextMonday.plus(2, ChronoUnit.WEEKS);
        String endDateStr = endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        logger.info("Setting end date to {}", endDateStr);
        WebElement endDateField = driver.findElement(endDateInput);
        endDateField.sendKeys(endDateStr);
        return this;
    }

    @Step("Select courses: {course1}, {course2}")
    public SelectPage selectCourse(String course1, String course2) {
        logger.info("Selecting courses: {}, {}", course1, course2);
        WebElement coursesDropdown = driver.findElement(coursesSelect);
        Select select = new Select(coursesDropdown);
        select.selectByVisibleText(course1);
        select.selectByVisibleText(course2);
        return this;
    }

    @Step("Click Search button")
    public SearchResultsPage clickSearchButton() {
        logger.info("Clicking Search button");
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.click();
        return new SearchResultsPage(driver);
    }
}
