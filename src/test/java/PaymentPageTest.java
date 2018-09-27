import Pages.WebPages;
import Utilities.Credentials;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class PaymentPageTest extends  BaseClass{



    public UserFactory userFactory = new UserFactory();

    public PaymentPageTest() throws MalformedURLException {
    }

    @Test
    public void MakeSuccessfulPayment() throws Exception {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());

        //Act
        WebPages.AdPostConfirmationPage(driver).goToMyAccount();

        //Assert

        // Assert.assertTrue(Pages.PaymentSuccessPage(driver).getUrl().contains("/posting_confirm.php"));
    }

    //The following test are invalid due to the new design of the payments page
    @Ignore
    @Test
    public void UnableToCompleteAPaymentForAnAdWithoutACardholderName() throws Exception {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());

        //Act
        WebPages.PlanPaymentPage(driver).makePayment(userFactory.basicUserNoCardholderName());

        //Assert
        Assert.assertEquals(Credentials.requiredFieldErrorMessage, WebPages.PlanPaymentPage(driver).cardHolderNameErrorMessage.getText());
    }

    @Ignore
    @Test
    public void UnableToCompleteAPaymentForAnAdWithoutACardholderNumber() throws Exception {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());

        //Act
        WebPages.PlanPaymentPage(driver).makePayment(userFactory.basicUserNoCardnumber());

        //Assert
        Assert.assertEquals(Credentials.requiredFieldErrorMessage, WebPages.PlanPaymentPage(driver).cardNumberErrorMessage.getText());
    }

    @Ignore
    @Test
    public void UnableToCompleteAPaymentForAnAdWithInvalidCardNumberLength() throws Exception {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());

        //Act
        WebPages.PlanPaymentPage(driver).makePayment(userFactory.basicUserInvalidCardnumberLength());

        //Assert
        Assert.assertEquals(Credentials.invalidCardLengthErrorMessage, WebPages.PlanPaymentPage(driver).invalidCardLengthErrorMessage.getText());
    }

    @Ignore
    @Test
    public void UnableToCompleteAPaymentForAnAdWithInvalidCardNumber() throws Exception {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());

        //Act
        WebPages.PlanPaymentPage(driver).makePayment(userFactory.basicUserInvalidCardnumberLength());

        //Assert
        Assert.assertEquals(Credentials.invalidCardLengthErrorMessage, WebPages.PlanPaymentPage(driver).invalidCardLengthErrorMessage.getText());
    }

    @Ignore
    @Test
    public void UnableToCompleteAPaymentForAnAdWithInvalidExpiry() throws Exception {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());

        //Act
        WebPages.PlanPaymentPage(driver).makePayment(userFactory.basicUserInvalidExpiry());

        //Assert
        Assert.assertEquals(Credentials.cardExpiryErrorMessage, WebPages.PlanPaymentPage(driver).cardExpiryErrorMessage.getText());
    }
}
