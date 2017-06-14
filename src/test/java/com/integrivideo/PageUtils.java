package com.integrivideo;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by asus on 6/7/2017.
 */
public class PageUtils extends PageObject{
    public WebElementFacade findElement(By elementBy, By containerBy, int timeoutSeconds, boolean isEnabled) {
        long startTime = Utils.getSeconds();
        while (Utils.getSeconds() - startTime <= timeoutSeconds) {
            List<WebElementFacade> elements;
            setImplicitTimeout(0, TimeUnit.SECONDS);
            if (containerBy == null) {
                elements = findAll(elementBy);
            } else {
                WebElementFacade container = findElement(containerBy, null, timeoutSeconds, true);
                elements = container.thenFindAll(elementBy);
            }
            resetImplicitTimeout();
            for (WebElementFacade element : elements)
                if (element.isCurrentlyVisible() && (element.isCurrentlyEnabled() == isEnabled)) {
                    return element;
                }
        }
        return null;
    }
}
