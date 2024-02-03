package lesson_2_hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditingPage {
    @FindBy(xpath = "//*[@class='material-icons mdc-icon-button mdc-icon-button--display-flex smui-icon-button--size-button mdc-icon-button--reduced-size mdc-ripple-upgraded--unbounded mdc-ripple-upgraded']")
    private WebElement editButton;

    @FindBy(xpath = "(//*[@class='mdc-text-field__input'])[1]")
    private WebElement nameInput;

    @FindBy(xpath = "//*[@class='button mdc-button mdc-button--raised mdc-ripple-upgraded']")
    private WebElement saveButton;

    // Локатор для ячеек с ID
    @FindBy(xpath = "//*[@class='mdc-data-table__cell mdc-data-table__cell--numeric']")
    private List<WebElement> idCells;

    private final WebDriver driver;

    public EditingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void enterName(String name) {
        nameInput.sendKeys(name);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    // Метод для поиска ID dummy по имени
    public String getDummyIdByName(String dummyName) {
        for (WebElement cell : idCells) {
            if (cell.getText().contains(dummyName)) {
                // Извлекаем текст из ячейки, содержащей ID dummy
                return cell.getText();
            }
        }
        // Если не найдено совпадений, возвращаем null
        return null;
    }
}