package lesson_2;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePostPage {
    @FindBy(xpath = "//*[@type='text']")
    private WebElement title;
    @FindBy(xpath = "//textarea[@class='mdc-text-field__input']")
    private WebElement description;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement buttonSave;

    public CreatePostPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void savePost(String titleText, String descriptionText){
        title.sendKeys(titleText);
        description.sendKeys(descriptionText);
        buttonSave.click();
    };
}
