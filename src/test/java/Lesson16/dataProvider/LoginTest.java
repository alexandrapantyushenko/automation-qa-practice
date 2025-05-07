package Lesson16.dataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    // DataProvider для трех пользователей
    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][] {
                { "user1", "password1" }, // Данные для первого пользователя
                { "user2", "password2" }, // Данные для второго пользователя
                { "user3", "password3" }  // Данные для третьего пользователя
        };
    }

    // Тест, который использует данные из DataProvider
    @Test(dataProvider = "userData")
    public void testLogin(String username, String password) {
        // Логика проверки логина. Например, используя некий метод login.
        boolean isLoginSuccessful = login(username, password);

        // Проверяем, что логин успешен
        assertTrue(isLoginSuccessful, "Login failed for user: " + username);
    }

    // Метод для имитации процесса логина
    public boolean login(String username, String password) {
        // Имитация логики логина (например, через API или UI)
        // Это может быть заменено реальной логикой, как вызов сервиса или взаимодействие с UI.
        return username.startsWith("user") && password.startsWith("password");
    }
}

