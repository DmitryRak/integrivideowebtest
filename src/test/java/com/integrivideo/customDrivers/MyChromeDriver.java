package com.integrivideo.customDrivers;

import com.integrivideo.Data;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class MyChromeDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.default_directory", Data.DOWNLOAD_FOLDER);
            chromePrefs.put("plugins.always_open_pdf_externally", true);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.setExperimentalOption("prefs", chromePrefs);

            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

            return new ChromeDriver(desiredCapabilities);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}