package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TimeoutHelper {


    public static void WaitforElement(WebDriverWait wait, LocatorType locatorType, String locator) {

        wait.withTimeout(Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(5));
        wait.ignoring(NoSuchElementException.class);

        switch (locatorType){
            case Id:
                if (   wait.until(ExpectedConditions.titleIs(locator))){

                    wait.until(ExpectedConditions.titleIs(locator));
                }


                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
            case XPath:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            case LinkText:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
            case Classname:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
            case CSS:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        }

    }



    public static void WaitforTitle(WebDriverWait wait,LocatorType locatorType,String locator) {

        wait.withTimeout(Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(5));
        wait.ignoring(NoSuchElementException.class);

        switch (locatorType){
            case Id:
                if (   wait.until(ExpectedConditions.titleIs(locator))){

                    wait.until(ExpectedConditions.titleIs(locator));
                }


                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
            case XPath:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            case LinkText:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
            case Classname:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
            case CSS:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        }

    }
}
