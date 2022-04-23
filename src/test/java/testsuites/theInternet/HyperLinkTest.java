package testsuites.theInternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class HyperLinkTest {
    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/status_codes");
    }

    @Test
    void redirect(){
        String href = driver.findElement(By.linkText("200")).getAttribute("type");

        System.out.println(href);
        driver.findElement(By.linkText("200")).click();
//        driver.findElement(By.linkText("here")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/200");
        driver.navigate().back();

        driver.findElement(By.linkText("301")).click();
//        driver.findElement(By.linkText("here")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/301");

        driver.navigate().back();

        driver.findElement(By.linkText("404")).click();
//        driver.findElement(By.linkText("here")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/404");

        driver.navigate().back();

        driver.findElement(By.linkText("500")).click();
//        driver.findElement(By.linkText("here")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes/500");

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/status_codes");
    }

    @AfterMethod
    void tearDown(ITestResult result) throws IOException {
        /**
         * Capture screenshot when test failed only
         */
        String testName = result.getMethod().getMethodName();

        if(!result.isSuccess()) {
            System.out.printf("Test : %s is FAIL\n",testName);
            testName = testName +"-"+System.currentTimeMillis();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(String.format("./target/screen-shots/%s.png",testName)));
        }else {
            System.out.printf("Test : %s is PASS\n",testName);
        }

//        driver.quit();
    }
}
