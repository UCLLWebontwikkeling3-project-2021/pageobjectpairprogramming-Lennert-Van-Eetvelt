    /**
     * @Author Lennert Van Eetvelt
     */

package PageObjectTests;

import org.openqa.selenium.WebDriver;

public class LogoutPage extends Page {

    public LogoutPage (WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Logout");
    }

}
