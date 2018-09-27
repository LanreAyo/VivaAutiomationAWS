package Pages;

import Base.BasePage;
import Utilities.LocatorType;
import Utilities.TimeoutHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdPostConfirmationPage extends BasePage {


    public WebDriver driver;
    WebDriverWait wait;

    TimeoutHelper timeouthelper;

    public AdPostConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        timeouthelper = new TimeoutHelper();
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "input[value='Go to your account']")
    public WebElement goToYourAccountBtn;


    @FindBy(how = How.CSS, using ="input[value='Post a New Ad']" )
    public WebElement postNewAdBtn;



    //Methods
    public void goToMyAccount(){

        timeouthelper.WaitforElement(wait, LocatorType.CSS,"input[value='Go to your account']");
    }
}
