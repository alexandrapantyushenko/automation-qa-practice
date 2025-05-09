package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SelectPage {

    private WebDriver driver;

    public SelectPage(WebDriver driver) {
        this.driver = driver;
    }

    private By countrySelect = By.xpath("//select[@title='Select country']");
    private By languageSelect = By.xpath("//select[@title='Select language']");
    private By typeSelect = By.xpath("//select[@title='Select type']");
    private By startDateInput = By.xpath("//input[@title = 'Start date']");
    private By endDateInput = By.xpath("//input[@title = 'End date']");
    private By coursesSelect = By.xpath("//select[@id='MultipleSelect']");
    private By searchButton = By.xpath("//button[@type='submit']");
   // private By errorMessage = By.xpath("");

    public SelectPage selectCountry(String country) {
        WebElement countryDropdown = driver.findElement(countrySelect);
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(country);
        return this;
    }

    public SelectPage selectLanguage(String language) {
        WebElement languageDropdown = driver.findElement(languageSelect);
        Select select = new Select(languageDropdown);
        select.selectByVisibleText(language);
        return this;

    }

    public SelectPage selectType(String type) {
        WebElement typeDropdown = driver.findElement(typeSelect);
        Select select = new Select(typeDropdown);
        select.selectByVisibleText(type);
        return this;
    }

    public SelectPage setStartDate() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(DayOfWeek.MONDAY).plusWeeks(1);
        String startDate = nextMonday.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        WebElement startDayField = driver.findElement(startDateInput);
        startDayField.sendKeys(startDate);
        return this;
    }

    public SelectPage setEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(java.time.DayOfWeek.MONDAY).plusWeeks(1);
        LocalDate endDate = nextMonday.plus(2, ChronoUnit.WEEKS);
        String endDateStr = endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        WebElement endDateField = driver.findElement(endDateInput);
        endDateField.sendKeys(endDateStr);
        return this;
    }

    public SelectPage selectCourse(String course1, String course2) {
        WebElement coursesDropdown = driver.findElement(coursesSelect);
        Select select = new Select(coursesDropdown);

        select.selectByVisibleText(course1);
        select.selectByVisibleText(course2);
        return this;
    }

    public SearchResultsPage clickSearchButton(){
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.click();
        return new SearchResultsPage(driver);
    }
}











