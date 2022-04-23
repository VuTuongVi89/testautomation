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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class CartAmountTest {
    static private WebDriver driver;
    static private  WebDriverWait wait;

    @BeforeMethod
    void setUp(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /**
     * 1. go to amazon.com
     * 2. add 2-3 product to cart
     * 3. go to cart
     * 4. verify total amount is correct.
     */
    @Test
    void addCart(){
        driver.get("https://www.amazon.com");
        searchProduct("ferrari 488 GTE");
        selectFirstSearchItem();
        handleShippingLocation();
        addToCart();
        goToCart();

        int itemCount = 0;
        double subTotal = 0.0;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-name='Active Items']"))).isDisplayed();
        List<WebElement> cartActiveItems =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-name='Active Items']/div/@data-asin")));

        for(WebElement item : cartActiveItems){
            int quantity = parseInt(item.getAttribute("data-quantity"));
            double price = Double.parseDouble(item.getAttribute("data-price"));

            itemCount += quantity;
            subTotal += quantity*price;
        }



        double subTotalAmountActiveCart = Double.parseDouble(driver.findElement(By.name("sc-subtotal-amount-activecart"))
                .findElement(By.tagName("span")).getText().replace("$",""));

        Assert.assertEquals(subTotalAmountActiveCart,subTotal);
        Assert.assertEquals(getTotalItemsActiveCart(),itemCount);
    }

    private int getCartCount() {
        return parseInt(driver.findElement(By.id("nav-cart-count")).getText());
    }

    private void goToCart(){
        driver.findElement(By.id("nav-cart-count")).click();
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

    public int getTotalItemsActiveCart(){
        String subTotalLabelActiveCart = driver.findElement(By.name("sc-subtotal-label-activecart")).getText();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(subTotalLabelActiveCart);
        while(m.find()) return parseInt(m.group());
        return 0;
    }


}
