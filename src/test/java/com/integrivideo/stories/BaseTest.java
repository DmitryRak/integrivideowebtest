package com.integrivideo.stories;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.SerenityWebdriverManager;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */

public class BaseTest {

    @Managed(driver="chrome", uniqueSession = true)
    public WebDriver driver;

    //http://stackoverflow.com/questions/38751525/firefox-browser-is-not-opening-with-selenium-webbrowser-code
    @Before
    public void setUp(){
        SerenityWebdriverManager.inThisTestThread()
                .registerDriverCalled(
                        ((WebDriverFacade) driver).getDriverName()
                ).forDriver(driver);
        setDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
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
