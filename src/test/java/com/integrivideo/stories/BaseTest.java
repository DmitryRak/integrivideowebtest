package com.integrivideo.stories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public abstract class BaseTest {

    WebDriver driver;
    //TODO move to maven option
    private static final String driverString = "chrome";

    //http://stackoverflow.com/questions/38751525/firefox-browser-is-not-opening-with-selenium-webbrowser-code
    @BeforeMethod
    public void setUp(){
        setDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private void setDriver() {
        System.out.println("OS: " + System.getProperty("os.name") + "; Browser: " + driverString);
        if(driverString.contains("firefox")){
            System.setProperty("webdriver.gecko.driver", "target\\classes\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else{
            if (System.getProperty("os.name").contains("OS X"))
                System.setProperty("webdriver.chrome.driver", "target\\classes\\chromedriver");
            else
                System.setProperty("webdriver.chrome.driver", "target\\classes\\chromedriver.exe");
            driver = new ChromeDriver();
        }
}
}
