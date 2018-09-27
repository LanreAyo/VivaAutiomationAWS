import Base.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass extends Browser{


    public BaseClass() {
    }

    @BeforeMethod
    public void initialise() throws MalformedURLException, NoSuchMethodException {

       super.initialise();

        System.out.println("this is before");
    }
    @AfterMethod
    public void tearDown(){

        super.tearDown();

        System.out.println("this is after");
    }


}
