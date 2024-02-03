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

public class EditingPageTest {
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

        WebElement editButton = driver.findElement(By.xpath("(//*[@class='material-icons mdc-icon-button mdc-icon-button--display-flex smui-icon-button--size-button mdc-icon-button--reduced-size mdc-ripple-upgraded--unbounded mdc-ripple-upgraded'])[2]"));
        editButton.click();

        String dummyNameEdit = "Саня";
        WebElement nameInputEdit = driver.findElement(By.xpath("(//*[@class='mdc-text-field__input'])[1]"));
        nameInputEdit.sendKeys(dummyNameEdit);

        WebElement saveButton = driver.findElement(By.xpath("//*[@class='button mdc-button mdc-button--raised mdc-ripple-upgraded']"));
        saveButton.click();

        // После сохранения можно получить ID dummy по имени
        EditingPage editingPage = new EditingPage(driver);
        String dummyId = editingPage.getDummyIdByName("Саня");

        // Проверяем, что ID dummy был найден и не равен null
        Assertions.assertNotNull(dummyId, "ID для dummy 'Саня' не найден на странице Dummies");
    }
}
