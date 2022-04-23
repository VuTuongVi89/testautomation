package testsuites.theInternet;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicLoadingTest extends BaseTest {

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        driver.navigate().to(URL+"/dynamic_loading/1");
    }

    @Test
    void waitLoadingComplete() {
        driver.findElement(By.id("start")).findElement(By.tagName("button")).click();

        String results = waitUntilElementPresent(By.id("finish"))// check the locator is show in DOM && Show in UI
                .findElement(By.tagName("h4")).getText();

        Assert.assertEquals(results,"Hello World!");
    }

    private WebElement waitUntilElementPresent(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitUntilElementClickabe(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
