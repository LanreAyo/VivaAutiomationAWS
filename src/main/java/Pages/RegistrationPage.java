package Pages;

import Base.BasePage;
import Base.Browser;
import Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    public WebDriver driver ;
    WebDriverWait wait;
    TimeoutHelper timeouthelper;

    String currentUrl;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        timeouthelper = new TimeoutHelper();
        PageFactory.initElements(driver, this);
    }

    @FindBy(how=How.ID, using="reg_email")
    public WebElement txtRegistrationEmail;

    @FindBy(how=How.ID, using="reg_email_confirm")
    public WebElement txtConfirmRegistrationEmail;

    @FindBy(how=How.ID, using="reg_password")
    public WebElement txtRegistrationPassword;

    @FindBy(how=How.ID, using="ind_radio")
    public WebElement chkUserTypeIndividual;

    @FindBy(how=How.ID, using="pro_radio")
    public WebElement chkUserTypeProfessional;

    @FindBy(how=How.ID, using="reg_username")
    public WebElement txtRegistrationUsername;

    @FindBy(how=How.ID, using="reg_company_name")
    public WebElement txtCompanyName;

    @FindBy(how=How.ID, using="reg_company_address")
    public WebElement txtFullAddress;

    @FindBy(how=How.CSS, using="input.kiwii-btn-primary.kiwii-font-small.kiwii-btn-large.kiwii-btn.kiwii-margin-right-small")
    public WebElement btnSubmit;

    @FindBy(how=How.ID, using="error_bubble_reg_email")
    public WebElement txtEmailError;

    @FindBy(how=How.ID, using="error_bubble_reg_email_confirm")
    public WebElement txtEmailConfirmError;

    @FindBy(how=How.ID, using="error_bubble_reg_password")
    public WebElement txtPasswordError;

    @FindBy(how=How.ID, using="error_bubble_reg_username")
    public WebElement txtUsernameError;

    @FindBy(how=How.ID, using="error_bubble_reg_company_name")
    public WebElement txtCompanyNameError;

    @FindBy(how=How.ID, using="error_bubble_reg_company_address")
    public WebElement txtFullAddressError;

    @FindBy(how=How.ID, using="error_message_block")
    public WebElement existingEmailErrormessage;



    public void registerNewIndividualUser(User user) throws Exception {
        setRegistrationEmail(user.email);
        setConfirmRegistrationEmail(user.email);
        setRegistrationPassword(user.password);
        selectUserType(user.userType);
        setRegistrationUsername(user.username);
        timeouthelper.WaitforElement(wait,LocatorType.CSS,"input.kiwii-btn-primary.kiwii-font-small.kiwii-btn-large.kiwii-btn.kiwii-margin-right-small");
        selectRegistrationSubmit();
    }

    public void registerInvalidUser(String Email, String Password, String Username, String Type, String Company, String Address) {
        if (Email.contains("Valid")) {
            setRegistrationEmail(Credentials.UnregisteredEmail);
            setConfirmRegistrationEmail(Credentials.UnregisteredEmail);
        } else if (Email.contains("Invalid")) {
            setRegistrationEmail(Credentials.InvalidEmail);
            setConfirmRegistrationEmail(Credentials.InvalidEmail);
        }
        if (Password.contains("Valid")) {
            setRegistrationPassword(Credentials.RegisteredPassword);
        } else if (Password.contains("Invalid Short")) {
            setRegistrationPassword(Credentials.InvalidPasswordShort);
        }
        if (Type.contains("Individual")) {
            selectUserTypeIndividual();
            if (Username.contains("Valid")) {
                setRegistrationUsername(Credentials.NewUsername);
            } else if (Username.contains("Invalid")) {
                setRegistrationUsername(Credentials.InvalidUsername);
            }
        }
        else {
            selectUserTypeProfessional();
            if (Company.contains("Valid")) {
                txtCompanyName.sendKeys(Credentials.NewCompany);
            }
            if (Address.contains("Valid")) {
                txtFullAddress.sendKeys(Credentials.NewAddress);
            }
        }
        btnSubmit.click();
        if(!driver.getCurrentUrl().contains("authorize.php")){
            throw new IllegalStateException("This is not the registration page");
        }
    }

    public void registerNewIndividualUserInvalid(User user) throws Exception {
        setRegistrationEmail(user.email);
        setConfirmRegistrationEmail(user.email);
        setRegistrationPassword(user.password);
        selectUserType(user.userType);
        setRegistrationUsername(user.username);
        btnSubmit.click();
    }

    public void registerNewIndividualUserNoEmail(User user) throws Exception {
        setRegistrationEmail(user.email);
        setConfirmRegistrationEmail(user.email);
        setRegistrationPassword(user.password);
        selectUserType(user.userType);
        setRegistrationUsername(user.username);
        btnSubmit.click();
    }

    public void registerNewProfessionalUser(User user) throws Exception {
        setRegistrationEmail(user.email);
        setConfirmRegistrationEmail(user.email);
        setRegistrationPassword(user.password);
        selectUserType(user.userType);
        txtCompanyName.sendKeys(user.company);
        txtFullAddress.sendKeys(user.address);
        selectRegistrationSubmit();
    }

    public void registerInvalidProfessionalUser(User user) throws Exception {
        setRegistrationEmail(user.email);
        setConfirmRegistrationEmail(user.email);
        setRegistrationPassword(user.password);
        selectUserType(user.userType);
        txtCompanyName.sendKeys(user.company);
        txtFullAddress.sendKeys(user.address);
        btnSubmit.click();
    }

    public void selectUserType(UserType userType) throws Exception {
        switch (userType){
            case Individual:
                chkUserTypeIndividual.click();
                break;
            case Professional:
                chkUserTypeProfessional.click();
                break;

            default:
                throw  new Exception(  "User Type Not found");
        }
    }


    public String currentPageUrl() {
        currentUrl = driver.getCurrentUrl();

        return currentUrl;
    }

    public void setRegistrationEmail(String RegistrationEmailValue) {
        txtRegistrationEmail.sendKeys(RegistrationEmailValue);
    }

    public void setConfirmRegistrationEmail(String ConfirmRegistrationEmailValue) {
        txtConfirmRegistrationEmail.sendKeys(ConfirmRegistrationEmailValue);
    }

    public void setRegistrationPassword(String RegistrationPasswordValue) {
        txtRegistrationPassword.sendKeys(RegistrationPasswordValue);
    }

    public void selectUserTypeIndividual() {
        chkUserTypeIndividual.click();
    }

    public void selectUserTypeProfessional() {
        chkUserTypeProfessional.click();
    }

    public void setRegistrationUsername(String RegistrationUsernameValue) {
        txtRegistrationUsername.sendKeys(RegistrationUsernameValue);
    }

    public MyAccountPage selectRegistrationSubmit() {
        btnSubmit.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a.kiwii-xxxdark-gray.kiwii-no-text-decoration"))));
        //Browser.checkPageUrl("account.php?account_notify", "This is not user account page");
        return new MyAccountPage(driver);
    }
}
