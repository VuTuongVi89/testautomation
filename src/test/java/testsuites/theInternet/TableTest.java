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

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TableTest extends BaseTest {

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL+"/tables");
    }

    /**
     * <table id="table1" class="tablesorter">
     *     <thead>
     *       <tr>
     *         <th class="header headerSortUp"><span>Last Name</span></th>
     *         <th class="header"><span>First Name</span></th>
     *         <th class="header"><span>Email</span></th>
     *         <th class="header"><span>Due</span></th>
     *         <th class="header"><span>Web Site</span></th>
     *         <th class="header"><span>Action</span></th>
     *       </tr>
     *     </thead>
     *     <tbody>
     *     <tr>
     *         <td>Smith</td>
     *         <td>John</td>
     *         <td>jsmith@gmail.com</td>
     *         <td>$50.00</td>
     *         <td>http://www.jsmith.com</td>
     *         <td>
     *           <a href="#edit">edit</a>
     *           <a href="#delete">delete</a>
     *         </td>
     *       </tr><tr>
     *         <td>Doe</td>
     *         <td>Jason</td>
     *         <td>jdoe@hotmail.com</td>
     *         <td>$100.00</td>
     *         <td>http://www.jdoe.com</td>
     *         <td>
     *           <a href="#edit">edit</a>
     *           <a href="#delete">delete</a>
     *         </td>
     *       </tr><tr>
     *         <td>Conway</td>
     *         <td>Tim</td>
     *         <td>tconway@earthlink.net</td>
     *         <td>$50.00</td>
     *         <td>http://www.timconway.com</td>
     *         <td>
     *           <a href="#edit">edit</a>
     *           <a href="#delete">delete</a>
     *         </td>
     *       </tr><tr>
     *         <td>Bach</td>
     *         <td>Frank</td>
     *         <td>fbach@yahoo.com</td>
     *         <td>$51.00</td>
     *         <td>http://www.frank.com</td>
     *         <td>
     *           <a href="#edit">edit</a>
     *           <a href="#delete">delete</a>
     *         </td>
     *       </tr></tbody>
     *   </table>
     */
    @Test
    void table1(){
        WebElement table1= driver.findElement(By.id("table1"));
        List<WebElement> dueColumn = table1.findElements(By.xpath("//td[4]"));
        List<WebElement> lastNameColumn = table1.findElements(By.xpath("//td[1]"));
        List<WebElement> firstNameColumn = table1.findElements(By.xpath("//td[2]"));

        List<Double> dues = dueColumn
                .stream()
                .map(cell -> Double.valueOf(cell.getText().replace("$","")))
                .collect(Collectors.toList());

        double maxDue = dues.stream().max(Double::compare).get();

        int maxDueIndex = 0;
        for(int i=1;i<dues.size();i++){
            if(dues.get(i)==maxDue){
                maxDueIndex = i;
            }
        }
        String largestDueFirstName = lastNameColumn.get(maxDueIndex).getText();
        String largestDueLastName = firstNameColumn.get(maxDueIndex).getText();
        Assert.assertEquals(String.format("%s %s",largestDueFirstName,largestDueLastName),"Doe Jason");//failed
    }


}
