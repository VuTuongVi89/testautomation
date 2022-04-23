package testsuites.theInternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class DropDownTest {
    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
        // run before all test method
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/dropdown");
    }

    /**
     *
     * <select id="dropdown">
     *     <option value="" disabled="disabled">Please select an option</option> 0
     *     <option value="1" selected="selected">Option 1</option> 1
     *     <option value="2">Option 2</option> 2
     * </select>
     *

     */

    /**
     * select option 1
     * validate option 1 is selected
     * select option 2
     * validate option 2 is selected
     */

    @Test
    void validateOption1IsSelected(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText("Option 1");
        WebElement option1 = driver.findElement(By.xpath("//option[.='Option 1']"));
        Assert.assertTrue(option1.isSelected());
    }

    @Test
    void validateOption2IsSelected(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText("Option 2");
        WebElement option1 = driver.findElement(By.xpath("//option[.='Option 2']"));
        Assert.assertTrue(option1.isSelected());
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
