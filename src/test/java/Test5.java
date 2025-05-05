import components.AndersenUrls;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;
import pages.HomePage;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test5 extends BaseTest {

    @Test
    void userLogin() {
        WebDriver driver = getDriver();

        // Шаг 1: Переход на страницу входа и авторизация
        SignInPage signInPage = new SignInPage(driver)
                .navigateTo(AndersenUrls.LOGIN.getUrl());

        // Вводим учетные данные и выполняем вход
        signInPage.insertCredentials("emily.johnson57@example.com", "EmilyPass123!")
                .clickLogin();

        // Ожидаем загрузки страницы аккаунта
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(AndersenUrls.ACCOUNT.getUrl()));

        // Шаг 2: Переход на домашнюю страницу после успешного входа
        HomePage homePage = new HomePage(driver);


        // Шаг 3: Клик по кнопке "Upload"
        homePage.clickUploadButton();

        // Переход на страницу с загрузкой фото
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='w-[95px]']"))); // Локатор для кнопки "Загрузить"
        uploadButton.click();

        // Шаг 1: Выбираем файл изображения из проводника
        File fileToUpload = new File("E:/My photos/study.motivation/maxresdefault (1).jpg");
        // Укажите путь к изображению
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(fileToUpload.getAbsolutePath());

        // Шаг 2: Ожидаем появления модального окна с подтверждением обновления фото
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal-class')]"))); // Локатор для модального окна
        System.out.println("Текст модального окна: " + modal.getText());

        // Шаг 3: Закрываем модальное окно
        WebElement closeModalButton = modal.findElement(By.xpath("//button[@class='close-modal-button']")); // Локатор для кнопки закрытия
        closeModalButton.click();

        // Шаг 4: Проверяем, что фото было обновлено
        WebElement updatedPhoto = driver.findElement(By.xpath("//img[@class='updated-photo-class']")); // Локатор для обновленного фото
        assertTrue(updatedPhoto.isDisplayed(), "Фото не обновлено!");

        // Дополнительная проверка: Убедимся, что старое фото больше не отображается
        WebElement oldPhoto = driver.findElement(By.xpath("//img[@class='old-photo-class']"));
        assertFalse(oldPhoto.isDisplayed(), "Старое фото все еще отображается!");

        System.out.println("Фото успешно обновлено.");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Закрываем браузер
        driver.quit();
    }
}
}