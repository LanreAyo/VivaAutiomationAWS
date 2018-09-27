package Base;

import Utilities.BrowserTypes;
import Utilities.DriversFactory;
import Utilities.Environments;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Browser {
    DriversFactory driversFactory ;
    protected static WebDriver driver;



    public Browser() {
        driversFactory = new DriversFactory();
    }

    public void initialise() throws MalformedURLException, NoSuchMethodException {

        driver=driversFactory.GetDriver(BrowserTypes.RemoteChromeDriver);
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(Environments.GetEnvironment(Environments.TestEnvironments.Stage));
    }

    public  void tearDown(){
        driver.close();
    }

    public static void checkPageUrl(String url, String message) {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        if(!driver.getCurrentUrl().contains(url)){
            throw new IllegalStateException(message);
        }

    }


}
