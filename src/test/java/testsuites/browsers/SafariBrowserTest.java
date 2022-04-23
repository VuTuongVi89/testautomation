package testsuites.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SafariBrowserTest {

    /**
     * Todo: open safari browser then navigate to google.com.vn
     */

    @Test
    void navigateToGoogle(){
        // launch safari app
        WebDriver driver = new SafariDriver();

        //navigate to google.com.vn
        driver.navigate().to("https://google.com.vn");

        // validate the current url match expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");

        // quit browser
        driver.quit();

    }
}
