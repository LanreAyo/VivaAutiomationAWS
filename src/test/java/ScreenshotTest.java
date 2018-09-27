import Pages.WebPages;
import Utilities.ScreenshotHelper;
import Utilities.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class ScreenshotTest extends  BaseClass {
    public UserFactory userFactory = new UserFactory();
    public  String testName;


    public ScreenshotTest() throws MalformedURLException {
        super();
    }


    @Test
    public void loginPageScreenshot() throws Exception {
        testName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ScreenshotHelper.setChildDirectory(testName);
        WebPages.HomePage(driver).clickMyAccount();
        ScreenshotHelper.takeScreenShot(driver, testName);
        Assert.assertTrue(ScreenshotHelper.compareImages(testName)==true);
    }


    @Test
    public void myDetailsPageScreenshot() throws Exception {
        testName= Thread.currentThread().getStackTrace()[1].getMethodName();
        ScreenshotHelper.setChildDirectory(testName);
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(userFactory.RegisteredUser());
        WebPages.MyAccountPage(driver).btnMyDetails.click();

        ScreenshotHelper.takeScreenShot(driver, testName);
        Assert.assertTrue(ScreenshotHelper.compareImages(testName)==true);
    }

    @Test
    public void myAdsPageScreenshot() throws Exception {
        testName= Thread.currentThread().getStackTrace()[1].getMethodName();
        ScreenshotHelper.setChildDirectory(testName);
        WebPages.HomePage(driver).clickPostAd();
        ScreenshotHelper.takeScreenShot(driver, testName);
    }


    @Test
    public  void alertPageScreenshot() throws IOException {
        testName= Thread.currentThread().getStackTrace()[1].getMethodName();
        WebPages.HomePage(driver).clickMyAccount();
        WebPages.LoginPage(driver).loginValidAccount(userFactory.RegisteredUser());
        ScreenshotHelper.takeScreenShot(driver, testName);
        Assert.assertTrue(ScreenshotHelper.compareImages(testName)==true);
    }
}
