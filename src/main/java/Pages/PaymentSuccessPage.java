package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentSuccessPage extends BasePage {


    public WebDriver driver;
    WebDriverWait wait;

    public PaymentSuccessPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
