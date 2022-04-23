package testsuites.theInternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class HoverTest {

    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
        // run before all test method
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/hovers");
    }

    @Test
    void hoverAvatar(){
        Actions actions = new Actions(driver);
        WebElement avatar1 = driver.findElement(By.xpath("//div[@class='figure'][1]"));

        actions
                .moveToElement(avatar1)
                .perform();

        WebElement captionLbl = driver.findElement(By.xpath("//h5[.='name: user1']"));
        Assert.assertTrue(captionLbl.isDisplayed());
    }

    @AfterMethod
    void tearDown(ITestResult result) throws IOException {

        String testName = result.getMethod().getMethodName();

        if(!result.isSuccess()) {
            System.out.printf("Test : %s is FAIL\n",testName);
            testName = testName +"-"+System.currentTimeMillis();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(String.format("./target/screen-shots/%s.png",testName)));
        }else {
            System.out.printf("Test : %s is PASS\n",testName);
        }

        driver.quit();
    }
}
