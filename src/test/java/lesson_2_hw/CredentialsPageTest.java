package lesson_2_hw;

import lesson_2.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class CredentialsPageTest {
    @Test
    void loginTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://test-stand.gb.ru/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInSystem("GB202212c8b3e9", "b3b37b4d9c");

        WebElement credButton = driver.findElement(By.xpath("(//*[@class='material-icons mdc-icon-button mdc-icon-button--display-flex smui-icon-button--size-button mdc-icon-button--reduced-size mdc-ripple-upgraded--unbounded mdc-ripple-upgraded'])[1]"));
        credButton.click();

        // Проверяем наличие заголовка "Dummy credentials"
        WebElement titleElement = driver.findElement(By.xpath("//*[@id='simple-title']"));
        Assertions.assertTrue(titleElement.isDisplayed(), "Заголовок 'Dummy credentials' не отображается на странице");

        // Проверяем наличие текста "Login: user_QeVARxK6"
        WebElement contentFirstElement = driver.findElement(By.xpath("//*[@id='simple-content']"));
        String expectedFirstText = "Login: user_QeVARxK6";
        String actualFirstText = contentFirstElement.getText();
        Assertions.assertTrue(actualFirstText.contains(expectedFirstText), "Текст не найден на странице");

        // Проверяем наличие текста "PW: a0a26b269d"
        WebElement contentSecondElement = driver.findElement(By.xpath("//*[@id='simple-content']"));
        String expectedSecondText = "PW: a0a26b269d";
        String actualSecondText = contentSecondElement.getText();
        Assertions.assertTrue(actualSecondText.contains(expectedSecondText), "Текст не найден на странице");

        //Проверяем наличие кнопки Close
        WebElement closeButton = driver.findElement(By.xpath("(//div[@class='mdc-button__ripple'][1]/parent::button//span[@class='mdc-button__label'][1])[contains(text(), 'Close')]"));
        Assertions.assertNotNull(closeButton, "Кнопка Close не найдена на странице");
    }
}
