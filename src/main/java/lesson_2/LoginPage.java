package lesson_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(xpath = "//*[@type='text']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class='mdc-button__label']")
    private WebElement button;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void loginInSystem(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        button.click();
    }
}
