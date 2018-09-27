import Pages.WebPages;
import Utilities.Credentials;
import Utilities.User;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MyAccountTest extends  BaseClass {


    public UserFactory userFactory = new UserFactory();

    public MyAccountTest() throws MalformedURLException {
    }

    @Test
    public void PopulateIndividualProfileFields() throws Exception {

        //SCENARIO: Populate Individual profile fields
        //GIVEN that a user is logged in with an individual account
        //AND the user is on the My Details page
        //WHEN the user provides valid information in the fields
        //AND ticks/unticks the Email preferences tickboxes
        //AND the user clicks on the Submit button
        //THEN the information entered is saved

        User user = userFactory.validUser();
        WebPages.LoginPage(driver).createNewUser(user);

        //Arrange

        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).changeAccountDetails(Credentials.Gender);

        //Assert
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtLocation.getAttribute("value").equals(Credentials.ValidPostCode1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtPhone.getAttribute("value").equals(Credentials.ValidPhoneNumber1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtDescription.getAttribute("value").equals(Credentials.ValidInformation1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtInterests.getAttribute("value").equals(Credentials.ValidActivites1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtUrl.getAttribute("value").equals(Credentials.ValidWebsite1));
    }

    @Test
    public void PopulateProfessionalProfileFields() throws Exception {

        //SCENARIO: Populate Professional profile fields
        //GIVEN that a user is logged in with a professional account
        //AND the user is on the My Details page
        //WHEN the user provides valid information in the fields
        //AND ticks/unticks the Email preferences tickboxes
        //AND the user clicks on the Submit button
        //THEN the information entered is saved

        //Arrange

        User user = userFactory.registerProUser();
        WebPages.LoginPage(driver).createNewUser(user);

        //Act

        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).clickMyDetails();
        WebPages.MyAccountPage(driver).changeProfessionalAccountDetails();

        //Assert
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtCompanyName.getAttribute("value").equals(Credentials.ValidCompanyName1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtCompanyAddress.getAttribute("value").equals(Credentials.ValidCompanyAddress1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtPostCode.getAttribute("value").equals(Credentials.ValidPostCode1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtPhoneNumber1.getAttribute("value").equals(Credentials.ValidPhoneNumber1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtPhoneNumber2.getAttribute("value").equals(Credentials.ValidPhoneNumberTwo1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtContactName.getAttribute("value").equals(Credentials.Username1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtUrl.getAttribute("value").equals(Credentials.ValidWebsite1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtYourActivity.getAttribute("value").equals(Credentials.ValidActivites1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtShopName.getAttribute("value").equals(Credentials.ValidShopName1));
        Assert.assertTrue(WebPages.MyAccountPage(driver).getRepost().equals("Yes"));
        Assert.assertTrue(WebPages.MyAccountPage(driver).getReport().equals("Yes"));
        Assert.assertTrue(WebPages.MyAccountPage(driver).getProduct().equals("Yes"));
        Assert.assertTrue(WebPages.MyAccountPage(driver).getPartners().equals("Yes"));
    }

    @Test
    public void UnableToUpdateAnIndividualProfileWithoutRequiredFields() throws Exception {

        //SCENARIO: Unable to update an Individual profile without required fields
        //GIVEN that a user is logged in with an individual account
        //AND the user is on the My Details page
        //WHEN the user has the Email field blank
        //AND the user has the Username field blank
        //AND the user has the Location field blank
        //AND the user clicks on the Submit button
        //THEN the information entered is not saved
        //AND a warning message appears

        //Arrange

        User user = userFactory.validUser();
        WebPages.LoginPage(driver).createNewUser(user);

        //Act

        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).clickMyDetails();
        WebPages.MyAccountPage(driver).clearIndividualDetailsFields();

        //Assert

        // Assert.assertTrue(Pages.MyAccountPage(driver).txtNoEmailError.isDisplayed());
        Assert.assertTrue(WebPages.MyAccountPage(driver).invalidEmailError.isDisplayed());
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtNoLocationError.isDisplayed());

    }

    @Test
    public void UnableToUpdateAProfessionalProfileWithoutRequiredFields() throws Exception {

        //SCENARIO: Unable to update a Professional profile without required fields
        //GIVEN that a user is logged in with an Professional account
        //AND the user is on the My Details page
        //WHEN the user has the Company Name field blank
        //AND the user has the Full Address field blank
        //AND the user has the Postcode field blank
        //AND the user has the Email Address field blank
        //AND the user clicks on the Submit button
        //THEN the information entered is not saved
        //AND a warning message appears

        //Arrange

        User user = userFactory.registerProUser();

        WebPages.LoginPage(driver).createNewUser(user);

        //Act

        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).btnMyDetails.click();
        WebPages.MyAccountPage(driver).clearProfessionalDetailsFields();

        //Assert

        Assert.assertTrue(WebPages.MyAccountPage(driver).txtEmail.getAttribute("value").isEmpty());
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtCompanyName.getAttribute("value").isEmpty());
        Assert.assertTrue(WebPages.MyAccountPage(driver).txtCompanyAddress.getAttribute("value").isEmpty());
        Assert.assertTrue(WebPages.MyAccountPage(driver).btnSubmitMain.isDisplayed());

    }

    @Test
    public void UnableToUpdateTheEmailFieldWithAnInvalidEmail() throws Exception {

        //SCENARIO: Unable to update the email field with an invalid email
        //GIVEN that a user is logged in with an account
        //AND the user is on the My Details page
        //WHEN the user enters an invalid email in the Email field
        //AND the user clicks on the Submit button
        //THEN the email is not updated
        //AND an error message appears

        //Arrange

        User user = userFactory.validUser();

        WebPages.LoginPage(driver).createNewUser(user);

        //Act

        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).btnMyDetails.click();
        WebPages.MyAccountPage(driver).txtEmail.clear();
        WebPages.MyAccountPage(driver).txtEmail.sendKeys(Credentials.InvalidEmail);
        WebPages.MyAccountPage(driver).changePostCode(Credentials.ValidPostCode1);
        WebPages.MyAccountPage(driver).btnSubmitMain.click();

        //Assert
        Assert.assertTrue(WebPages.MyAccountPage(driver).invalidEmailError.isDisplayed());
        //Assert.assertTrue(Pages.MyAccountPage(driver).txtErrorEmail.isDisplayed());

    }

    @Test
    public void UnableToUpdateTheURLFieldWithAnInvalidURL() throws Exception {

        //SCENARIO: Unable to update the URL field with an invalid URL
        //GIVEN that a user is logged in with an account
        //AND the user is on the My Details page
        //WHEN the user enters an invalid URL in the URL field
        //AND the user clicks on the Submit button
        //THEN the email is not updated
        //AND an error message appears

        //Arrange

        User user = userFactory.validUser();

        WebPages.LoginPage(driver).createNewUser(user);

        //Act

        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).btnMyDetails.click();
        WebPages.MyAccountPage(driver).txtUrl.clear();
        WebPages.MyAccountPage(driver).txtUrl.sendKeys(Credentials.InvalidURL);
        WebPages.MyAccountPage(driver).changePostCode(Credentials.ValidPostCode1);
        WebPages.MyAccountPage(driver).btnSubmitMain.click();

        //Assert

        Assert.assertTrue(WebPages.MyAccountPage(driver).txtInvalidURL.isDisplayed());

    }

    @Test
    public void ChangeEmailAddressForAccount() throws Exception {

        //SCENARIO: Change email address for account
        //GIVEN that a user is logged in with an account
        //AND the user is on the My Details page
        //WHEN the user enters a different valid email in the Email field
        //AND the user clicks on the Submit button
        //THEN the email is not updated
        //AND an error message appears

        //Arrange

        User user = userFactory.validUser();

        WebPages.LoginPage(driver).createNewUser(user);

        String firstEmail = user.email;
        String secondEmail = Credentials.RegisteredIndividual2;
        String firstPassword = user.password;
        String secondPassword = Credentials.registeredPassword;

        //Act

        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).btnMyDetails.click();
        WebPages.MyAccountPage(driver).txtEmail.clear();
        WebPages.MyAccountPage(driver).txtEmail.sendKeys(secondEmail);
        //Pages.MyAccountPage(driver).txtLocation.clear();
        //Pages.MyAccountPage(driver).txtLocation.sendKeys(Credentials.ValidLocation1);
        WebPages.MyAccountPage(driver).btnSubmitMain.click();
        user.email = secondEmail;
        user.password = secondPassword;
        WebPages.MyAccountPage(driver).lnkLogout.click();
        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).btnMyDetails.click();
        WebPages.MyAccountPage(driver).txtEmail.clear();
        WebPages.MyAccountPage(driver).txtEmail.sendKeys(firstEmail);
        //Pages.MyAccountPage(driver).txtLocation.clear();
        //Pages.MyAccountPage(driver).txtLocation.sendKeys(Credentials.ValidLocation1);
        user.email = firstEmail;
        user.password = firstPassword;
        WebPages.MyAccountPage(driver).btnSubmitMain.click();
        WebPages.MyAccountPage(driver).lnkLogout.click();
        WebPages.HomePage(driver).lnkMyAccount.click();
        WebPages.LoginPage(driver).loginValidAccount(user);
        WebPages.MyAccountPage(driver).btnMyDetails.click();

        //Assert

        WebPages.MyAccountPage(driver).checkEmailField(user.email);
    }


}
