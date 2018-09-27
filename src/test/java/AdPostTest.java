import Pages.WebPages;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AdPostTest extends  BaseClass{

    public UserFactory userFactory = new UserFactory();

    public AdPostTest() throws MalformedURLException {
    }

    @Test
    public void CreateAValidAdAdultDating() throws InterruptedException {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        //Act
        WebPages.AdPostPage(driver).ValidPostAd(userFactory.basicUser());
        //Assert
        Assert.assertTrue(WebPages.AdPostPage(driver).getTitle().equals("Vivastreet Secure Payments"));
    }

    @Test
    public void UnableToCreateAnAdWithMissingPostcode() throws InterruptedException {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();

        //Act
        WebPages.AdPostPage(driver).InvalidPostAd(userFactory.noPostCodeUser());
        //Assert

        Assert.assertEquals( WebPages.AdPostPage(driver).msgEnterYourPostCode.getText(),"Enter your postcode");
    }

    @Test
    public void UnableToCreateAnAdWithMissingTitle() throws InterruptedException {

        //Arrange
        WebPages.HomePage(driver).clickPostAd();
        //Act
        WebPages.AdPostPage(driver).InvalidPostAd(userFactory.noTitleUser());
        //Assert
        Assert.assertEquals(WebPages.AdPostPage(driver).msgMissingTitle.getText(),"Make sure your title is clear and relevant to your ad");
    }

}
