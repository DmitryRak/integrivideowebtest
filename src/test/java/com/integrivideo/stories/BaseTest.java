package com.integrivideo.stories;

import io.github.bonigarcia.wdm.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public abstract class BaseTest {

    WebDriver driver;
    private static String driverString = "chrome";

    //http://stackoverflow.com/questions/38751525/firefox-browser-is-not-opening-with-selenium-webbrowser-code
    @Before
    public void setUp(){
        setDriver();
       /* driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();*/
    }

    @After
    public void tearDown(){
        //driver.quit();
    }

    private void setDriver() {
        if(System.getProperty("browser")!=null){
            driverString = System.getProperty("browser");
        }
        System.out.println("OS: " + System.getProperty("os.name") + "; Browser: " + driverString);
        if(driverString.contains("firefox")){
            FirefoxDriverManager.getInstance().setup();
            //driver = new FirefoxDriver();
        }else if(driverString.contains("edge")){
            EdgeDriverManager.getInstance().setup();//to handle alerts in EDGE http://stackoverflow.com/questions/26772793/org-openqa-selenium-unhandledalertexception-unexpected-alert-open
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            //driver = new EdgeDriver(dc);
        }else if(driverString.contains("ie")){
            InternetExplorerDriverManager.getInstance().setup();
            //Also workaround from this place is required: http://jimevansmusic.blogspot.com.by/2012/08/youre-doing-it-wrong-protected-mode-and.html#iesettings
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability(
                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                    true);
            //workaround to click SHIFT
            caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            //driver = new InternetExplorerDriver(caps);
        }else if(driverString.contains("opera")){
            OperaDriverManager.getInstance().setup();
            //driver = new OperaDriver();
        }else {
            ChromeDriverManager.getInstance().setup();
            String id = System.getProperty("webdriver.chrome.driver");
            //driver = new ChromeDriver();
        }
    }
}
