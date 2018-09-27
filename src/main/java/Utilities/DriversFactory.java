package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriversFactory {

    public WebDriver driver;


    //This method gets the driver
    public WebDriver GetDriver(BrowserTypes browserTypes) throws MalformedURLException {

        switch (browserTypes) {
            case Chrome:

                driver = new ChromeDriver();

                break;


            case Firefox:

                driver = new FirefoxDriver();

            case RemoteChromeDriver:

                DesiredCapabilities desiredCapabilities=DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub") ,desiredCapabilities);
        }

        return driver;
    }
}