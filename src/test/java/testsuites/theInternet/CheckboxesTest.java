package testsuites.theInternet;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.amazon.SignUpPage;
import pages.theInternet.CheckboxesPage;
import supports.Browser;

import java.io.File;
import java.io.IOException;

public class CheckboxesTest extends BaseTest {
    CheckboxesPage checkboxesPage;

    @BeforeMethod // run before all test method
    void setUp(String browser, String url)
    {
        this.driver = Browser.openBrowser(browser);
        checkboxesPage = new CheckboxesPage(driver);
    }

    /**
     * Check checkbox 1
     * validate checkbox 1 is checked
     *
     * un-check checkbox 2
     * valid checkbox 2 is not checked
     */

    /**
     * <form id="checkboxes">
     *     <input type="checkbox"> checkbox 1<br>
     *     <input type="checkbox" checked=""> checkbox 2
     * </form>
     */
    @Test
    void validateCheckbox1IsChecked(){
        checkboxesPage.validateCheckbox1IsChecked();
    }

    @Test
    void validateCheckbox2IsUnchecked(){
        checkboxesPage.validateCheckbox2IsUnchecked();
    }

}
