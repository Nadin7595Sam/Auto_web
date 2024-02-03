package lesson_2_hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;

public class CredentialsPage {
    @FindBy(xpath = "//*[@type='text']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class='mdc-button__label']")
    private WebElement button;

    @FindBy(xpath = "(//*[@class='material-icons mdc-icon-button mdc-icon-button--display-flex smui-icon-button--size-button mdc-icon-button--reduced-size mdc-ripple-upgraded--unbounded mdc-ripple-upgraded'])[1]")
    private WebElement credButton;

    public CredentialsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void loginInSystem(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        button.click();
    }

    public void clickCredButton() {
        credButton.click();
    }
}
