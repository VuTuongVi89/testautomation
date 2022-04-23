package pages.theInternet;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.URL;

public class CheckboxesPage extends BaseTest {
    WebDriver driver;
    By checkboxesCbx = By.id("checkboxes");
    By checkboxesCbx1 = By.xpath("//input[1]");
    By checkboxesCbx2 = By.xpath("//input[2]");

    public CheckboxesPage(WebDriver driver) {this.driver = driver;}
    public void navigate (){driver.get(URL+"/checkboxes");}
    public void validateCheckbox1IsChecked()
    {
        WebElement checkboxes = driver.findElement(checkboxesCbx); // the parent element
        WebElement checkbox1 = checkboxes.findElement(checkboxesCbx1);// the first input child
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected(),"Checkbox 1 is not checked"); // verify element state is checked or not
    }
    public void validateCheckbox2IsUnchecked(){
        WebElement checkboxes = driver.findElement(checkboxesCbx); //the parent element
        WebElement checkbox2 = checkboxes.findElement(checkboxesCbx2); // the second input child
        checkbox2.click();
        Assert.assertFalse(checkbox2.isSelected(),"Checkbox2 is checked");
    }

}
