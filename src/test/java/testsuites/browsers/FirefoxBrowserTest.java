package testsuites.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirefoxBrowserTest {
    /**
     * Todo: open firefox browser then navigate to google.com.vn
     */

    @Test
    void navigateToGoogle(){
        // set gecko driver path --> $PATH
        WebDriverManager.firefoxdriver().setup();

        // launch firefox app
        WebDriver driver = new FirefoxDriver();

        //navigate to google.com.vn
        driver.navigate().to("https://google.com.vn");

        // validate the current url match expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");

        // quit browser
        driver.quit();
    }

    @Test
    void navigateToGoogleWithHeadlessMode(){
        // set gecko driver path --> $PATH
        WebDriverManager.firefoxdriver().setup();

        // enable headless mode
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);

        // launch firefox app
        WebDriver driver = new FirefoxDriver(firefoxOptions);

        //navigate to google.com.vn
        driver.navigate().to("https://google.com.vn");

        // validate the current url match expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");

        // quit browser
        driver.quit();
    }
}
