package testsuites.amazon;

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
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class AddItemToCartTest {
    static private WebDriver driver;

    @BeforeMethod
    void setUp(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }


    // Domain Specified Languages
    // - DDD
    // TDD : Test Driven Development
    // BDD:  Behavior Driven Development
    // DDD:  Domain Driven Development

    /**
     * 1. Open Browser
     * 2. Navigate to amazon.com
     * 3. search "ferrari 488 GTE"
     * 4. click one of search results
     * hints: using driver.findElements() return List<WebElement> --> select by index
     * 5. click add to cart.
     * 6. validate cart item turn "1".
     */
    @Test
    void addCart(){
        driver.get("https://www.amazon.com");
        int cartCountBefore = getCartCount();

        // add first item
        searchProduct("ferrari 488 GTE");
        selectFirstSearchItem();
        handleShippingLocation();
        addToCart();
        int cartCountAfter = getCartCount();
        Assert.assertEquals(cartCountAfter-cartCountBefore, 1);

        // add second item
        searchProduct("Cat D11 Bulldozer Building");
        selectFirstSearchItem();
        handleShippingLocation();
        addToCart();
    }

    private int getCartCount() {
        return parseInt(driver.findElement(By.id("nav-cart-count")).getText());
    }

    private void addToCart() {
        driver.findElement(By.id("add-to-cart-button")).click();
    }

    private void handleShippingLocation() {
        if(driver.findElements(By.id("add-to-cart-button")).size() ==0){
           driver.findElement(By.id("contextualIngressPtLabel")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='or enter a US zip code']")))
                            .sendKeys("90202");
            driver.findElement(By.xpath("//span[.='Apply']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='a-popover-footer']//span[.='Continue' and @class='a-button-text']"))).click();
        }
    }

    private void selectFirstSearchItem() {
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));
        searchResults.get(0).click();
    }

    private void searchProduct(String productName) {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(productName+"\n");
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
