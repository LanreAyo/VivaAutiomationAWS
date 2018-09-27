package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public WebDriver driver;

    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
         this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver,this);
    }


    @FindBy(how=How.XPATH, using="//a[contains(.,'My Account')]")
    public WebElement lnkMyAccount;

    @FindBy(how=How.CSS,using="a[class='post']")
    public WebElement lnkPostAd;

    public LoginPage clickMyAccount(){
        lnkMyAccount.click();
       // Browser.checkPageUrl("login.php","This is not the Login page");
        return new LoginPage(driver);
    }

    public void clickPostAd() {

        lnkPostAd.click();
    }
}
