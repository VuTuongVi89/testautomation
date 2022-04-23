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

public class FrameSetTest {

    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/nested_frames");
    }


    @Test
    void getFrames(){
       WebDriver frameTop = driver.switchTo().frame("frame-top");
       WebDriver frameLeft = frameTop.switchTo().frame("frame-left");

       String frameLeftContent = frameLeft.findElement(By.xpath("/html/body")).getText();
        Assert.assertEquals(frameLeftContent,"LEFT");

        WebDriver frameMiddle= frameLeft.switchTo().parentFrame().switchTo().frame("frame-middle");
        String frameMiddleContent = frameMiddle.findElement(By.id("content")).getText();
        Assert.assertEquals(frameMiddleContent,"MIDDLE");

        WebDriver rightFrame = frameMiddle.switchTo().parentFrame().switchTo().frame("frame-right");
        String rightFrameContent = rightFrame.findElement(By.xpath("/html/body")).getText();
        Assert.assertEquals(rightFrameContent,"RIGHT");

        WebDriver bottomFrame = rightFrame
                .switchTo().parentFrame() // top-frame
                .switchTo().parentFrame() // origin driver
                .switchTo().frame("frame-bottom");


        String bottomFrameContent = bottomFrame.findElement(By.xpath("/html/body")).getText();
        Assert.assertEquals(bottomFrameContent,"BOTTOM");

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
