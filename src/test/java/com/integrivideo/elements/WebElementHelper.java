package com.integrivideo.elements;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class WebElementHelper {
    public static boolean elementHasClass(WebElement element, String className) {
        Reporter.log(element.getAttribute("class"), true);
        return element.getAttribute("class").contains(className);
    }
}
