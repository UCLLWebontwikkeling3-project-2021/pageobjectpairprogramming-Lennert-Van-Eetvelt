package PageObjectTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ui.controller.Register;
import ui.controller.RegisterTest;
import ui.controller.SignUp;

import static org.junit.Assert.*;

public class SearchTestPageObject {

    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "D:\\Users\\lenne\\AppData\\Local\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
        signUpPage.signUp("jqklsdfklmqjkl", "TestXYZE", "TestLastNameee", "email@mail.com", "VerrrysTronm156?");
    }

    @After
    public void clean() {
        RemovePage removePage = PageFactory.initElements(driver, RemovePage.class);
        removePage.removeUser("jqklsdfklmqjkl");
        driver.quit();
    }

    @Test
    public void no_test_find_nothing() {
        ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
        contactPage.addContact("first", "contact", "10/10/2020", "15:15", "+321231564", "a@b.co");
        contactPage.addContact("second", "contact", "15/12/2020", "15:15", "+321231564", "a@b.co");

        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        assertFalse(searchPage.containsContactWithName("first contact"));
        assertFalse(searchPage.containsContactWithName("second contact"));
        assertFalse(searchPage.hasNoContactsSinceLastTest());
        assertTrue(searchPage.hasNoTestMessage());
    }

    @Test
    public void test_find_only_one() {
        ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
        contactPage.addContact("first", "contact", "10/10/2020", "15:15", "+321231564", "a@b.co");
        contactPage.addContact("second", "contact", "15/12/2020", "15:15", "+321231564", "a@b.co");

        RegisterTestPage registerTestPage = PageFactory.initElements(driver, RegisterTestPage.class);
        registerTestPage.setDate("10/11/2020");
        registerTestPage.submitInvalid();

        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        assertTrue(searchPage.containsContactWithName("first contact"));
        assertFalse(searchPage.containsContactWithName("second contact"));
        assertFalse(searchPage.hasNoContactsSinceLastTest());
        assertFalse(searchPage.hasNoTestMessage());
    }

    @Test
    public void test_find_all() {
        ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
        contactPage.addContact("first", "contact", "10/10/2020", "15:15", "+321231564", "a@b.co");
        contactPage.addContact("second", "contact", "15/12/2020", "15:15", "+321231564", "a@b.co");

        RegisterTestPage registerTestPage = PageFactory.initElements(driver, RegisterTestPage.class);
        registerTestPage.setDate("10/05/2021");
        registerTestPage.submitInvalid();

        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        assertTrue(searchPage.containsContactWithName("first contact"));
        assertTrue(searchPage.containsContactWithName("second contact"));
        assertFalse(searchPage.hasNoContactsSinceLastTest());
        assertFalse(searchPage.hasNoTestMessage());
    }

    @Test
    public void test_find_none() {
        ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
        contactPage.addContact("first", "contact", "10/10/2020", "15:15", "+321231564", "a@b.co");
        contactPage.addContact("second", "contact", "15/12/2020", "15:15", "+321231564", "a@b.co");

        RegisterTestPage registerTestPage = PageFactory.initElements(driver, RegisterTestPage.class);
        registerTestPage.setDate("20/12/2019");
        registerTestPage.submitInvalid();

        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        assertFalse(searchPage.containsContactWithName("first contact"));
        assertFalse(searchPage.containsContactWithName("second contact"));
        assertTrue(searchPage.hasNoContactsSinceLastTest());
        assertFalse(searchPage.hasNoTestMessage());
    }
}