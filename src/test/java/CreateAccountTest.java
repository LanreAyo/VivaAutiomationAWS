import Pages.WebPages;
import Utilities.Credentials;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.net.MalformedURLException;

import static Pages.WebPages.RegistrationPage;

public class CreateAccountTest extends  BaseClass{

    public UserFactory userFactory = new UserFactory();

    public CreateAccountTest() throws MalformedURLException {
    }

    @Test
    public void registerForYourFreeAccount() throws Exception {
        System.out.println("register");
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
       WebPages.RegistrationPage(driver).registerNewIndividualUser(userFactory.validUser()); Assert.assertTrue(RegistrationPage(driver).currentPageUrl().contains(Credentials.confirmAccountUrl));
    }

    @Test
    public void createAValidIndividualAccount() throws Exception {
        System.out.println("create");
       WebPages.HomePage(driver).clickMyAccount();
      WebPages.LoginPage(driver).clickRegisterLink();
       WebPages.RegistrationPage(driver).registerNewIndividualUser(userFactory.validUser());
        Assert.assertTrue(WebPages.RegistrationPage(driver).currentPageUrl().contains(Credentials.confirmAccountUrl));
  }

    @Test
    public void unableToCreateAnAccountWithNoEmail() throws Exception {
        System.out.println("unable");
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
       WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.invalidUserNoEmail());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtEmailError.getText(),Credentials.emailErrorMessage);
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtEmailError.getText(),Credentials.confirmEmailErrorMessage);
    }

    @Test
    public void unableToCreateAnAccountWithMissingPassword() throws Exception {
        System.out.println("unable to create missing");
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.invalidUserNoPassword());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtPasswordError.getText(),Credentials.passwordErrorMessage);
    }

    @Test
    public void unableToCreateAnAccountWithMissingUsername() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.InvalidUserNoUsername());
        Assert.assertEquals( WebPages.RegistrationPage(driver).txtUsernameError.getText(),Credentials.usernameErrorMessage);
    }

    @Test
    public void unableToCreateAnAccountWithNoData() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.InvalidUserNoData());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtEmailError.getText(),Credentials.emailErrorMessage);
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtEmailConfirmError.getText(),Credentials.confirmEmailErrorMessage);
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtPasswordError.getText(),Credentials.passwordErrorMessage);
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtUsernameError.getText(),Credentials.usernameErrorMessage);
    }

    @Test
    public void unableToCreateAnAccountWithInvalidEmailAddress() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.InvalidUserInvalidEmail());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtEmailError.getText(), Credentials.invalidEmailErrorMessage);
    }

    @Test
    public void unableToCreateAnAccountWithRegisteredEmailAddress() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.RegisteredUserIncorrectPassword());
        Assert.assertEquals(WebPages.RegistrationPage(driver).existingEmailErrormessage.getText(), Credentials.emailExistsErrorMessage);
    }

    // The following test has been ignored as there is a bug on the live website which is yet to be fixed and as such this test will always fail.

//    @Test
//    public void unableToCreateAnAccountWithInvalidPassword() throws Exception {
//        HomePage homePage = new HomePage(driver);
//        LoginPage loginPagePage= homePage.clickMyAccount();
//        RegistrationPage registrationPage = loginPagePage.clickRegisterLink();
//        registrationPage.registerNewIndividualUserInvalid(userFactory.invalidUserInvalidPassword());
//        Assert.assertEquals(registrationPage.txtPasswordError.getText(),Credentials.passwordErrorMessage);
//
//    }

    @Test
    public void unableToCreateAnAccountWithAnInvalidUsername() throws Exception {
       WebPages.HomePage(driver).clickMyAccount();
       WebPages.LoginPage(driver).clickRegisterLink();
       WebPages.RegistrationPage(driver).registerNewIndividualUserInvalid(userFactory.InvalidUserNoUsername());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtUsernameError.getText(),Credentials.usernameErrorMessage);
    }

    @Test
    public void createAValidProfessionalAccount() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerNewProfessionalUser(userFactory.registerProUser());
        Assert.assertTrue(WebPages.RegistrationPage(driver).currentPageUrl().contains(Credentials.confirmAccountUrl));    }

    @Test
    public void unableToCreateAnAccountWithMissingCompanyName() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerInvalidProfessionalUser(userFactory.InvalidProUserNoCompany());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtCompanyNameError.getText(),Credentials.companyNameErrorMessage);
    }

    @Test
    public void unableToCreateAnAccountWithMissingFullAddress() throws Exception {
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).clickRegisterLink();
        WebPages.RegistrationPage(driver).registerInvalidProfessionalUser(userFactory.InvalidProUserNoAddress());
        Assert.assertEquals(WebPages.RegistrationPage(driver).txtFullAddressError.getText(),Credentials.companyAddressErrorMessage);
    }

}
