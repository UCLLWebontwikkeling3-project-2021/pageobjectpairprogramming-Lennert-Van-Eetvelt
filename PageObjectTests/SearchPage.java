    /**
     * @Author Lennert Van Eetvelt
     */

package PageObjectTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchPage extends Page {

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=Search");
    }

    public boolean containsContactWithName(String name) {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found = false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains(name)) {
                found = true;
            }
        }
        return found;
    }

    public boolean hasNoTestMessage() {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("h4"));
        boolean found = false;

        System.out.println(listItems);

        for (WebElement listItem : listItems) {
            System.out.println(listItem);
            if (listItem.getText().contains("no registered positive covid tests")) {
                found = true;
            }
        }
        return found;
    }

    public boolean hasNoContactsSinceLastTest() {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("h4"));
        boolean found = false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains("not been in contact with anyone since your last positive covid test")) {
                found = true;
            }
        }
        return found;
    }
}
