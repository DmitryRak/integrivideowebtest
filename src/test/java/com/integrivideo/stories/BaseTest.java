package com.integrivideo.stories;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
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
            System.setProperty("webdriver.gecko.driver", "target"+File.separator+"classes"+File.separator+"geckodriver.exe");
            driver = new FirefoxDriver();
        }else if(driverString.contains("edge")){
            System.setProperty("webdriver.edge.driver", "target"+File.separator+"classes"+File.separator+"MicrosoftWebDriver.exe");
            //to handle alerts in EDGE http://stackoverflow.com/questions/26772793/org-openqa-selenium-unhandledalertexception-unexpected-alert-open
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            driver = new EdgeDriver(dc);
        }else if(driverString.contains("ie")){
            System.setProperty("webdriver.ie.driver", "target"+File.separator+"classes"+File.separator+"IEDriverServer.exe");
            //Also workaround from this place is required: http://jimevansmusic.blogspot.com.by/2012/08/youre-doing-it-wrong-protected-mode-and.html#iesettings
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability(
                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                    true);
            //workaround to click SHIFT
            caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            driver = new InternetExplorerDriver(caps);
        }else {
            if (System.getProperty("os.name").contains("Mac")) {
                System.setProperty("webdriver.chrome.driver", "target" + File.separator + "classes" + File.separator + "chromedriver");
                try {
                    Runtime.getRuntime().exec("chmod +x 'target" + File.separator + "classes" + File.separator + "chromedriver'");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
                System.setProperty("webdriver.chrome.driver", "target"+File.separator+"classes"+File.separator+"chromedriver.exe");
            driver = new ChromeDriver();
        }
    }
}
