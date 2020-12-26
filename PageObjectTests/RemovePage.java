    /**
     * @Author Lennert Van Eetvelt
     */

package PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RemovePage extends Page {

    public RemovePage (WebDriver driver) {
        super(driver);
//        this.driver.get(getPath()+"?command=RemovePerson");
    }

    public void removeUser(String userID){
        this.driver.get(getPath()+"?command=RemovePerson&id="+userID);
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

}
