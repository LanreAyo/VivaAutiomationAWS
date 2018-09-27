package Pages;

import Base.BasePage;
import Utilities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlanPaymentPage extends BasePage {

    public WebDriver driver;
    WebDriverWait wait;

    public PlanPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "tab-credit_card")
    public WebElement btnCreditCard;

    @FindBy(how = How.ID, using = "holder_name")
    public WebElement txtCardHolderName;
    ;
    @FindBy(how = How.ID, using = "cc_number")
    public WebElement txtCardNumber;

    @FindBy(how = How.ID, using = "expiration_date")
    public WebElement txtExpiresOn;

    @FindBy(how = How.ID, using = "security_code")
    public WebElement txtSecurityCode;

    @FindBy(how = How.ID, using = "should_save")
    public WebElement btnSaveCard;

//    @FindBy(how = How.ID, using = "payment_submit")
//    public WebElement btnSubmitPayment;

    @FindBy(how = How.ID, using = "submit_credit_card")
    public WebElement btnSubmitPayment;


    @FindBy(how = How.ID, using = "vs_content")
    public WebElement msgConfirmationContent;
    @FindBy(how=How.CSS,using="div[class='cc_number']>div")
    public  WebElement cardNumberErrorMessage;

    @FindBy(how=How.CSS,using = "div[class='holder_name vs-full']>div")
    public WebElement cardHolderNameErrorMessage;

    @FindBy(how=How.XPATH,using = "//div[contains(text(),'Verify card number')]")
    public WebElement invalidCardLengthErrorMessage;


    @FindBy(how=How.XPATH,using ="//div[contains(text(),'Enter a valid date')]")
    public WebElement cardExpiryErrorMessage;

    public void makePayment(User user)throws Exception {
        btnCreditCard.click();
        txtCardHolderName.sendKeys(user.getcardHolderName());
        txtCardNumber.sendKeys(user.getCardNumber());
        txtExpiresOn.sendKeys(user.getCardExpiry());
        txtSecurityCode.sendKeys(user.getCardSecurityCode());
        btnSubmitPayment.click();
    }
}
