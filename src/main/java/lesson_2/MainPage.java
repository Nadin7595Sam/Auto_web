package lesson_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    @FindBy(id = "create-btn")
    WebElement createNewPost;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void createPost(){
        createNewPost.click();
    }
}
