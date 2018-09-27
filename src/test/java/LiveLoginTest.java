import Pages.WebPages;
import Utilities.Credentials;
import Utilities.Text;
import Utilities.User;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LiveLoginTest extends  BaseClass {


    public UserFactory userFactory = new UserFactory();

    public LiveLoginTest()  {
    }

    @Test
    public void loginPageLoadsCorrectly()  {

        WebPages.HomePage(driver).clickMyAccount();

        //Assert

        Assert.assertTrue(WebPages.LoginPage(driver).txtEmail.isDisplayed());
        Assert.assertTrue(WebPages.LoginPage(driver).txtPassword.isDisplayed());
        Assert.assertTrue(WebPages.LoginPage(driver).btnLogin.isDisplayed());
        Assert.assertTrue(WebPages.LoginPage(driver).lnkForgotPassword.isDisplayed());


  }


}
