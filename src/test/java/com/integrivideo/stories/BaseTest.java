package com.integrivideo.stories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public abstract class BaseTest {
    WebDriver driver;

    //http://stackoverflow.com/questions/38751525/firefox-browser-is-not-opening-with-selenium-webbrowser-code
    @BeforeMethod
    public void setUp(){
        setDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private static void setDriver() {
        System.out.println("OS: " + System.getProperty("os.name"));
        if (System.getProperty("os.name").contains("OS X"))
            System.setProperty("webdriver.chrome.driver", "target\\classes\\chromedriver");
//        else if (System.getProperty("os.name").contains("Linux"))
//            System.setProperty("webdriver.chrome.driver", "chromedriver");
        else
            System.setProperty("webdriver.chrome.driver", "target\\classes\\chromedriver.exe");
    }
}
