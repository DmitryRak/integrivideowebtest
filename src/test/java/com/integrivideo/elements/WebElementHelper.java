package com.integrivideo.elements;

import org.openqa.selenium.WebElement;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class WebElementHelper {
    public static boolean elementHasClass(WebElement element, String className) {
        return element.getAttribute("class").contains(className);
    }
}
