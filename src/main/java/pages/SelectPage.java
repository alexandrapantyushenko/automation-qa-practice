package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectPage {

    private WebDriver driver;

    public SelectPage(WebDriver driver){
        this.driver = driver;
    }

    private By countrySelect = By.xpath("//select[@title='Select country']");
    private By USASelect = By.xpath("//select[@title='Select country']/option[text()='USA']");



}
