package testsuites.theInternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormAuthenticationTest {
    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/login");
    }

    @Test
    void loginSuccessfullyWithValidCredentials() {
        submitCredentials("tomsmith","SuperSecretPassword!");

        Assert.assertEquals(driver.getCurrentUrl(), URL+"/secure");

        WebElement successMessageLbl = driver.findElement(By.className("success"));
        Assert.assertTrue(successMessageLbl.isDisplayed());
    }

    @Test
    void loginWithInvalidCredentials() {
        submitCredentials("tomsmith","SuperSecretPassword");
        WebElement errorMessageLbl = driver.findElement(By.className("error"));
        Assert.assertTrue(errorMessageLbl.isDisplayed());
    }

    void submitCredentials(String username,String password){
//        driver.findElement(By.id("username")).sendKeys(username);

        By usernameLocator = RelativeLocator
                .with(By.tagName("input"))
                .above(By.id("password"));

        By passwordLocator = RelativeLocator
                .with(By.tagName("input"))
                .below(By.id("username"));

        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    //  .//tutorials/AK35/drivers/chromedriver.exe

    @AfterMethod
    void tearDown() {
        driver.quit();
    }
}