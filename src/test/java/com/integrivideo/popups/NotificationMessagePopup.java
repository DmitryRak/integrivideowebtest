package com.integrivideo.popups;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class NotificationMessagePopup extends PageObject {
    private static final By MESSAGE_BY = By.xpath("//div[contains(@class,'alert')]");

    public String getNotificationText() {
        return find(MESSAGE_BY).getText();
    }
}
