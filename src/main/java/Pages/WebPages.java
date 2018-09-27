package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;

public class WebPages extends BasePage {


    public static HomePage HomePage(WebDriver driver) {
        return new HomePage(driver);
    }

    public static LoginPage LoginPage(WebDriver driver) {
        return  new LoginPage(driver);
    }

    public static RegistrationPage RegistrationPage(WebDriver driver) {
        return new RegistrationPage(driver);
    }

    public static MyAccountPage MyAccountPage(WebDriver driver) {
        return new MyAccountPage(driver);
    }

    public static AdPostPage AdPostPage(WebDriver driver) {
        return new AdPostPage(driver);
    }

    public static PlanPaymentPage PlanPaymentPage(WebDriver driver) {
        return new PlanPaymentPage(driver);
    }

    public static PaymentSuccessPage PaymentSuccessPage(WebDriver driver) { return new PaymentSuccessPage(driver);}

    public static AdPostConfirmationPage AdPostConfirmationPage (WebDriver driver) { return new AdPostConfirmationPage(driver);}

    public static CreateAlertPage CreateAlertPage(WebDriver driver) { return new CreateAlertPage(driver);}
}
