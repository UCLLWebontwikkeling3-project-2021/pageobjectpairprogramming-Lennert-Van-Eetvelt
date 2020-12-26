    /**
     * @Author Lennert Van Eetvelt
     */

package PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ContactPage extends Page {
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "date")
    private WebElement dateField;

    @FindBy(id = "hour")
    private WebElement hourField;

    @FindBy(id = "gsm")
    private WebElement gsmField;

    @FindBy(id = "email")
    private WebElement emailField;


    @FindBy(id = "addContact")
    private WebElement submitButton;

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=Contacts");
    }


    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setGsm(String gsm) {
        gsmField.clear();
        gsmField.sendKeys(gsm);
    }

    public void setHour(String hour) {
        hourField.clear();
        hourField.sendKeys(hour);
    }

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }


    public void addContact(String firstName, String lastName, String date, String hour, String gsm, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setDate(date);
        setHour(hour);
        setGsm(gsm);
        submitInvalid();
    }

    public void submitInvalid() {
        submitButton.click();
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}
