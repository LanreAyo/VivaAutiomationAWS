import Pages.WebPages;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class CreateAlertTest extends  BaseClass{


    public UserFactory userFactory = new UserFactory();

    public CreateAlertTest() throws MalformedURLException {
    }

    @Test
    public void CreateAlertSuccessfully() {

        //Arrange
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(userFactory.RegisteredUser());

        //Act
        WebPages.CreateAlertPage(driver).createAlert(userFactory.basicUser());

        //Assert
        Assert.assertTrue(WebPages.CreateAlertPage(driver).lblCreateAlertConfirmation.isDisplayed());

    }

    @Test
    public void DeleteAlertSuccessfully() {

        //Arrange
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(userFactory.RegisteredUser());
        WebPages.CreateAlertPage(driver).createAlert(userFactory.basicUser());
        WebPages.CreateAlertPage(driver).closeAlertConfirmation();

        //Act
        WebPages.CreateAlertPage(driver).deleteAlert(userFactory.basicUser());

        //Assert
        Assert.assertTrue(WebPages.CreateAlertPage(driver).msgAlertDeleteConfirmation.isDisplayed());

    }

    @Test
    public void ModifyAlertSuccessfully() {

        //Arrange
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(userFactory.RegisteredUser());
        WebPages.CreateAlertPage(driver).createAlert(userFactory.basicUser());
        WebPages.CreateAlertPage(driver).closeAlertConfirmation();

        //Act
        WebPages.CreateAlertPage(driver).modifyAlert(userFactory.basicUser());

        //Assert
        Assert.assertTrue(WebPages.CreateAlertPage(driver).msgAlertEditConfirmation.isDisplayed());

    }


}
