package pages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import supports.Browser;

import static supports.Browser.click;
import static supports.Browser.fill;

public class SignUpPage {
    String URL = "https://www.amazon.com/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3F_encoding%3DUTF8%26ref_%3Dnav_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&";
    WebDriver driver;

    By nameTxt = By.name("customerName");
    By emailTxt = By.name("email");
    By passwordTxt = By.name("password");
    By passwordCheckTxt = By.name("passwordCheck");
    By continueBtn = By.id("continue");
    By puzzleChallengeLbl = By.xpath("//*[contains(text(),'Solve this puzzle to protect your account')]");


    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get(URL);
    }

    public void fillRegistrationForm(String customerName, String email, String password) {

        fill(driver,nameTxt,customerName);
        fill(driver,emailTxt,email);
        fill(driver,passwordTxt,password);
        fill(driver,passwordCheckTxt,password);
        click(driver,continueBtn);
    }

    public boolean isPuzzleChallengeDisplayed() {
        return this.driver.findElement(puzzleChallengeLbl).isDisplayed();

    }
}
