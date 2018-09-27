package Pages;

import Base.BasePage;
import Base.Browser;
import Utilities.Credentials;
import Utilities.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {



    public WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }



    @FindBy(how = How.ID, using = "email")
    public WebElement txtEmail;

    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;

    @FindBy(how = How.CSS, using = "input[type=submit]")
    public WebElement btnLogin;

    @FindBy(how = How.ID, using = "login_forgot_password_link")
    public WebElement lnkForgotPassword;

    @FindBy(how = How.ID, using = "login_error")
    public WebElement txtLoginError;

    @FindBy(how = How.ID, using = "password_error")
    public WebElement txtPasswordError;

    @FindBy(how = How.ID, using = "email_error")
    public WebElement txtEmailError;

    @FindBy(how = How.CLASS_NAME, using = "vs-lightbox-infopane")
    public WebElement txtForgotPasswordModal;

    @FindBy(how = How.ID, using = "forgot_password_email")
    public WebElement txtForgotEmail;

    @FindBy(how = How.ID, using = "forgotPasswordSubmitButton")
    public WebElement btnResetPassword;

    @FindBy(how = How.ID, using = "register_link")
    public WebElement lnkRegister;

    @FindBy(how = How.ID, using = "register_link")
    public WebElement registerLink;



    //-----Methods
    public RegistrationPage clickRegisterLink() {
        registerLink.click();
      //  Browser.checkPageUrl("authorize.php","This is not the Registration page");

        return new RegistrationPage(driver);
    }

    public void setLoginEmail(String loginEmailValue) {
        txtEmail.sendKeys(loginEmailValue);
    }

    public void setLoginPassword(String loginPasswordValue) {
        txtPassword.sendKeys(loginPasswordValue);
    }


    public MyAccountPage logIn(String email, String password) {

        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();

        return new MyAccountPage(driver);
    }

    public MyAccountPage loginValidAccount(User user) {

        setLoginEmail(user.email);
        setLoginPassword(user.password);
        selectLogin();

        return new MyAccountPage(driver);
    }

    public void createNewUser(User user) throws Exception {

        String type = user.userType.toString();
        if (type.equals("Individual")) {
            WebPages.HomePage(driver).clickMyAccount();
           WebPages.LoginPage(driver).clickRegisterLink();
           WebPages.RegistrationPage(driver).registerNewIndividualUser(user);
        } else {
          WebPages.HomePage(driver).clickMyAccount();
            WebPages.LoginPage(driver).clickRegisterLink();
           WebPages.RegistrationPage(driver).registerNewProfessionalUser(user);
        }
        WebPages.MyAccountPage(driver).lnkLogout.click();
    }



    public MyAccountPage selectLogin() {

        btnLogin.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a.kiwii-xxxdark-gray.kiwii-no-text-decoration"))));
        Browser.checkPageUrl("/account_classifieds.php", "This is not the account classifieds page");
        return new MyAccountPage(driver);
    }

    public void logInWrong(String Email, String Password) {

        if (Email.contains("Registered")) {
            setLoginEmail(Credentials.RegisteredEmail);
        } else if (Email.contains("Unregistered")) {
            setLoginEmail(Credentials.UnregisteredEmail);
        } else if (Email.contains("Invalid")) {
            setLoginEmail(Credentials.InvalidEmail);
        }
        if (Password.contains("Correct")) {
            setLoginPassword(Credentials.RegisteredPassword);
        } else if (Password.contains("Incorrect")) {
            setLoginPassword(Credentials.IncorrectPassword);
        }
        btnLogin.click();
      //  Browser.checkPageUrl("login.php", "This is not the login page");
    }
//
//    public void sendPasswordResetEmail(String Email) {
//
//        lnkForgotPassword.click();
//        String Message1 = txtForgotPasswordModal.getText();
//        Assert.assertTrue(Message1.contains(Text.ForgotPasswordModal));
//        txtForgotEmail.sendKeys(Email);
//        btnResetPassword.click();
//        Browser.checkPageUrl("login.php", "This is not the login page");
//    }

    public String getLoginErrorMessage() {
        return txtLoginError.getText();
    }

    public String getLoginEmailError() {
        return txtEmailError.getText();
    }

    public String getLoginPasswordError() {
        return txtPasswordError.getText();
    }
}
