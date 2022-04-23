package testsuites.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChromeBrowserTest {
    /**
     * Todo: open chrome browser then navigate to google.com.vn
     */

    @Test
    void navigateToGoogle(){
        // set chrome driver path --> $PATH
        WebDriverManager.chromedriver().setup();

        // launch chrome app
        WebDriver driver = new ChromeDriver();

        //navigate to google.com.vn
        driver.navigate().to("https://google.com.vn");

        // validate the current url match expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");

        // quit browser
        driver.quit();
    }

    @Test
    void navigateToGoogleWithHeadlessMode(){
        // set chrome driver path --> $PATH
        WebDriverManager.chromedriver().setup();

        // enable headless mode
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);

        // launch chrome app
        WebDriver driver = new ChromeDriver(chromeOptions);

        //navigate to google.com.vn
        driver.navigate().to("https://google.com.vn");

        // validate the current url match expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");

        // quit browser
        driver.quit();
    }

    @Test
    void navigateToGoogleWithMobileViewMode(){
        // set chrome driver path --> $PATH
        WebDriverManager.chromedriver().setup();

        // setup mobile view for iPhone 12 Pro
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 390);
        deviceMetrics.put("height", 844);
        deviceMetrics.put("pixelRatio", 1.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // launch chrome app
        WebDriver driver = new ChromeDriver(chromeOptions);

        //navigate to google.com.vn
        driver.navigate().to("https://google.com.vn");

        // validate the current url match expected
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com.vn/");

        // quit browser
        driver.quit();
    }

    @Test
    void emulateGeoLocation(){
        // set chrome driver path --> $PATH
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(35.689487),
                Optional.of(139.691706),
                Optional.of(1)));
        driver.get("https://my-location.org/");
//        driver.quit();
    }

    @Test
    void reuseExistedCookies(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.example.com");

        // Adds the cookie into current browser context
        driver.manage().addCookie(new Cookie("key", "value"));

    }
}
