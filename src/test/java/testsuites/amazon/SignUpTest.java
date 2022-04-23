package testsuites.amazon;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.amazon.SignUpPage;
import supports.JavaUtils;
import supports.Browser;

public class SignUpTest extends BaseTest {
    SignUpPage signUpPage;


    @Parameters({"browser","url"})
    @BeforeMethod
    void setUp(String browser,String url){
        this.driver = Browser.openBrowser(browser);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    void validInfo(){
        signUpPage.navigateTo();
        signUpPage.fillRegistrationForm( JavaUtils.generateCustomerName(), JavaUtils.generateEmail(), JavaUtils.generatePassword());
        Assert.assertTrue(signUpPage.isPuzzleChallengeDisplayed());
    }

}
