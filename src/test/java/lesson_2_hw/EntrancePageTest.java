package lesson_2_hw;

import lesson_2.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EntrancePageTest {
    private EntrancePage loginPage;

    @Test
    void loginTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://test-stand.gb.ru/login");

        // Входим в систему без ввода логина и пароля
        EntrancePage entrancePage = new EntrancePage(driver);
        entrancePage.loginInSystem("", "");

        WebElement button = driver.findElement(By.xpath("//*[@class='mdc-button__label']"));
        button.click();

        // Проверяем, что текст ошибки "Invalid credentials" отображается на странице
        List<WebElement> errorMessages = driver.findElements(By.xpath("//*[@class='error-block svelte-uwkxn9']"));
        Assertions.assertEquals(1, errorMessages.size(), "Текст ошибки не совпадает");
    }
}
