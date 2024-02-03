package lesson_1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class FirstTaskTest {
    static WebDriver driver;
    String login = "GB202306a2eba2";
    String password = "969b309794";

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
    void authorization() throws InterruptedException{
       WebElement loginInput = driver.findElement(By.xpath("//*[@type='text']"));
       WebElement passwordInput = driver.findElement(By.xpath("//*[@type='password']"));
        WebElement loginButton = driver.findElement(By.className("mdc-button__label"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        // Нажимаем кнопку LOGIN
        loginButton.click();
        List<WebElement> searchElement;
        searchElement = driver.findElements(By.partialLinkText("Hello, "));
        Assertions.assertEquals(1, searchElement.size());
    }

    @AfterAll
    static void closeApp(){
        driver.quit();
    }
}
