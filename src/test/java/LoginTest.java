import Pages.WebPages;
import Utilities.Credentials;
import Utilities.Text;
import Utilities.User;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoginTest extends  BaseClass {

    public UserFactory userFactory = new UserFactory();

    public LoginTest() throws MalformedURLException {
    }

    @Test
    public void loginPageLoadsCorrectly() {

        WebPages.HomePage(driver).clickMyAccount();

        //Assert

        Assert.assertTrue(WebPages.LoginPage(driver).txtEmail.isDisplayed());
        Assert.assertTrue(WebPages.LoginPage(driver).txtPassword.isDisplayed());
        Assert.assertTrue(WebPages.LoginPage(driver).btnLogin.isDisplayed());
        Assert.assertTrue(WebPages.LoginPage(driver).lnkForgotPassword.isDisplayed());
    }

    @Test
    public void logInWithARegisteredAccount() throws Exception {

        User user = userFactory.validUser();
        WebPages.LoginPage(driver).createNewUser(user);

        //Act

        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(user);

        //Assert

        Assert.assertTrue(WebPages.MyAccountPage(driver).lnkLogout.isDisplayed());
    }

    @Test
    public void unableToLogInWithUnregisteredAccount() {

        //Act

       WebPages.HomePage(driver).clickMyAccount();
       WebPages.LoginPage(driver).logInWrong("Unregistered", "Incorrect");

        //Assert

        String Message = WebPages.LoginPage(driver).getLoginErrorMessage();
        Assert.assertTrue(Message.contains(Text.IncorrectLoginMessage));
    }

    @Test
    public void unableToLogInWithIncorrectPassword() {

        //Act

        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).logInWrong("Registered", "Incorrect");

        //Assert

        String Message = WebPages.LoginPage(driver).getLoginErrorMessage();
        Assert.assertTrue(Message.contains(Text.IncorrectLoginMessage));
    }

    @Test
    public void unableToLogInWithoutAnEmail() {

        //Act

        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).logInWrong("Blank", "Incorrect");

        //Assert

        String Message = WebPages.LoginPage(driver).getLoginEmailError();
        Assert.assertTrue(Message.contains(Text.NoEmailMessage));

    }

    @Test
    public void unableToLogInWithoutAPassword() {

        //Act

       WebPages.HomePage(driver).clickMyAccount();
       WebPages.LoginPage(driver).logInWrong("Unregistered", "Blank");

        //Assert

        String Message = WebPages.LoginPage(driver).getLoginPasswordError();
        Assert.assertTrue(Message.contains(Text.NoPasswordMessage));

    }

    @Test
    public void unableToLogInWithBlankFields() {

        //Act

        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).logInWrong("Blank", "Blank");

        //Assert

        String Message1 = WebPages.LoginPage(driver).getLoginEmailError();
        String Message2 = WebPages.LoginPage(driver).getLoginPasswordError();
        Assert.assertTrue(Message1.contains(Text.NoEmailMessage));
        Assert.assertTrue(Message2.contains(Text.NoPasswordMessage));
    }

    @Test
    public void resetPasswordViaMyAccount() throws Exception {

        User user = userFactory.validUser();
        String firstPassword = user.password;
        String secondPassword = Credentials.ChangePassword;

        WebPages.LoginPage(driver).createNewUser(user);
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(user);
        // Browser.checkPageUrl("account_classifieds.php", "This is not account login page");
        WebPages.MyAccountPage(driver).clickMyDetails();
        WebPages.MyAccountPage(driver).changePassword(firstPassword, secondPassword);


        Assert.assertTrue(WebPages.MyAccountPage(driver).txtPasswordChangeSuccessMessage.isDisplayed());
    }
}
