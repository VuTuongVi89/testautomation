package testsuites.theInternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class JsAlertTest {

    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/javascript_alerts");
    }

    @Test
    void click(){
        driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        String actionContent= driver.findElement(By.id("result")).getText();
        Assert.assertEquals(actionContent,"You successfully clicked an alert");
    }

    @Test
    void dismissAlert(){
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();

        String actionContent= driver.findElement(By.id("result")).getText();
        Assert.assertEquals(actionContent,"You clicked: Cancel");
    }

    @Test
    void sendKeys(){
        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.alertIsPresent()).sendKeys("hello");
        driver.switchTo().alert().accept();

        String actionContent= driver.findElement(By.id("result")).getText();
        Assert.assertEquals(actionContent,"You entered: hello");
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

        driver.quit();
    }
}
