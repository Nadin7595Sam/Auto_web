package lesson_2_hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EntrancePage {
    @FindBy(xpath = "//*[@class='mdc-button__label']")
    private WebElement button;

    public EntrancePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    void loginInSystem(String s, String string){
        button.click();
    }
}
