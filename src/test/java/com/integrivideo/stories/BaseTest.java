package com.integrivideo.stories;

import net.thucydides.core.annotations.Managed;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
public class BaseTest {

    @Managed(driver = "chrome", uniqueSession = true)
    WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        getDriver().manage().window().maximize();
    }
}
