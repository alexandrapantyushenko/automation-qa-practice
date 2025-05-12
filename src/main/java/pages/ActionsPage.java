package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsPage {

    private WebDriver driver;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By confirmButton = By.xpath("//button[@id='AlertButton']");
    private By getDiscountButton = By.xpath("//button[contains (text(), 'Get Discount')]");
    private By cancelCourseButton = By.xpath("//button[@data-test-id='PromptButton']");

    public ActionsPage handleFirstAlert(){
        WebElement confirmButtonElement = driver.findElement(confirmButton);
        confirmButtonElement.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if(alertText.equals("You have called alert!")){
            alert.accept();
        } else {
            throw new RuntimeException("Alert text did't match");
        }
        return this;
    }

    public ActionsPage handleSecondAlert(){

        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(getDiscountButton)).perform();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if(alertText.equals("Are you sure you want to apply the discount?")){
             alert.accept();
        }else{
            throw new RuntimeException("Second alert text didn't match!");
        }
        return this;
    }


    public ActionsPage handleThirdAlertWithInput(String inputText){


    }








}
