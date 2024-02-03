import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FirstHomeTaskTest {
    static WebDriver driver;
    String login = "GB202212c8b3e9";
    String password = "b3b37b4d9c";
    @BeforeAll
    static void initElements() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized"); //режим полного экрана
        driver = new ChromeDriver(chromeOptions);
        //chromeOptions.addArguments("--headless"); //неявная функция
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    void test() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized"); //режим полного экрана
        WebDriver driver = new ChromeDriver(chromeOptions);
        //chromeOptions.addArguments("--headless"); //неявная функция
        driver.get("https://test-stand.gb.ru/login");
        Thread.sleep(5000L);
        driver.quit();
    }

    @Test
    void authorization() throws InterruptedException, IOException {
        WebElement loginInput = driver.findElement(By.xpath("//*[@type='text']"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@type='password']"));
        WebElement loginButton = driver.findElement(By.className("mdc-button__label"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);

        // Нажимаем кнопку LOGIN
        loginButton.click();

        // Создаем экземпляр WebDriverWait с таймаутом в 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Дожидаемся появления элемента "//h1[text()='Student Page']" на странице
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Student Page']")));

        // Нажимаем кнопку '+' для добавления имени
        WebElement addButton = driver.findElement(By.xpath("//*[@class='material-icons mdc-icon-button mdc-icon-button--display-flex mdc-ripple-upgraded--unbounded mdc-ripple-upgraded']"));
        addButton.click();

        // Дожидаемся появления модального окна с текстом "Creating Dummy" в заголовке
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@slot='title' and text()='Creating Dummy']")));

        // Генерируем уникальное имя на основе текущего времени
        String dummyName = "Саня" + System.currentTimeMillis();

        // Вводим имя
        WebElement nameInput = driver.findElement(By.xpath("(//input[@class='mdc-text-field__input' and @type='text' and @placeholder=' '])[1]"));
        nameInput.sendKeys(dummyName);

        // Генерируем случайный логин
        String randomLogin = "user_" + RandomStringUtils.randomAlphanumeric(8); // Генерируем логин из 8 случайных символов

        // Вводим логин
        WebElement loginDummyInput = driver.findElement(By.xpath("(//input[@class='mdc-text-field__input' and @type='text' and @placeholder=' '])[3]"));
        loginDummyInput.sendKeys(randomLogin);

        // Нажимаем кнопку "SAVE" после ввода имени и логина
        WebElement saveButton = driver.findElement(By.xpath("//span[@class='mdc-button__label' and text()='Save']"));
        saveButton.click();

        // Дожидаемся появления искомого элемента после сохранения Dummy в таблице
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='mdc-data-table__cell' and text()='" + dummyName + "']")));

        // Проверяем, что Dummy с указанным именем появился в таблице
        WebElement dummyInTable = driver.findElement(By.xpath("//td[@class='mdc-data-table__cell' and text()='" + dummyName + "']"));
        assertNotNull(dummyInTable, "Dummy с именем '" + dummyName + "' не найден в таблице");

        // Теперь создаем скриншот окна браузера
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Путь для сохранения скриншота в ресурсы
        String resourcesPath = "src/test/resources/";

        // Копируем скриншот в ресурсы
        Files.copy(screenshot.toPath(), new File(resourcesPath + "screenshot.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterAll
    static void closeApp(){
        driver.quit();
    }
}
