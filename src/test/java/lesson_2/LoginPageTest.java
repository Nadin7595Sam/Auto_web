package lesson_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class LoginPageTest {
    @Test
    void loginTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://test-stand.gb.ru/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginInSystem("annucka4", "6ec1fae7f2");

        List<WebElement> searchList = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, searchList.size());

        MainPage mainPage = new MainPage(driver);
        mainPage.createPost();

        List<WebElement> title = driver.findElements(By.xpath("//*[@type='text']"));
        Assertions.assertEquals(1, title.size());

        CreatePostPage createPostPage = new CreatePostPage(driver);
        createPostPage.savePost("Открытие нового хобби", "Поделюсь своими впечатлениями от нового хобби");

        Thread.sleep(5000L);
        ElementPage elementPage = new ElementPage(driver);
        elementPage.deletePost();

//        driver.quit();
    }
}
