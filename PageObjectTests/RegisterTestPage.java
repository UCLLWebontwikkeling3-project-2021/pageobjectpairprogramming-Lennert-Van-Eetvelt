    /**
     * @Author Lennert Van Eetvelt
     */

package PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class RegisterTestPage extends Page {
    @FindBy(id="date")
    private WebElement dateField;

    @FindBy(id="addTestResult")
    private WebElement submitButton;

    public RegisterTestPage (WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=RegisterTest");
    }


    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void submitInvalid() {
       submitButton.submit();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }
}
