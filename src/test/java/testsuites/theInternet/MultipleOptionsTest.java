package testsuites.theInternet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MultipleOptionsTest {
    WebDriver driver;
    String URL = "https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm";

    @BeforeMethod
        // run before all test method
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL);
    }

    /**
     *
     *<select multiple="" name="selenium_commands" >
     *    <option>Browser Commands</option>
     *    <option>Navigation Commands</option>
     *    <option>Switch Commands</option>
     *    <option>Wait Commands</option>
     *    <option>WebElement Commands</option>
     *</select>
     */

    @Test
    void selectMultipleOptions() throws InterruptedException {
        //identify dropdown
        WebElement d= driver.findElement(By.xpath("//select[@name='selenium_commands']"));

        //object of Select class
        Select s=new Select(d);
        //get options of dropdown in list
        List<WebElement> options =s.getOptions();
        System.out.println("Options are: ");
        for (WebElement option: options){
            System.out.println(option.getText());
        }
        //return true if multi-select dropdown
        Boolean b=s.isMultiple();

        System.out.println("Boolean value for dropdown: "+ b);
        //select item by index
        s.selectByIndex(1);
        s.selectByIndex(2);
        Thread.sleep(1000);
        //select item by visible text
        s.selectByVisibleText("Wait Commands");
        Thread.sleep(1000);
        //get first selected option in dropdown
        WebElement f = s.getFirstSelectedOption();
        System.out.println("First selected option is: "+ f.getText());
        //deselect option by index
        s.deselectByIndex(2);
        Thread.sleep(1000);
        //deselect all selected items
        s.deselectAll();
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
