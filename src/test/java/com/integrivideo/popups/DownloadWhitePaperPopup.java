package com.integrivideo.popups;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class DownloadWhitePaperPopup extends PageObject {

    private final static String POPUP_LOCATOR = "//div[contains(@id,'white-paper-modal')]";

    public void enterEmail(String email) {
        find(By.xpath(POPUP_LOCATOR.concat("//input[contains(@name,'email')]"))).sendKeys(email);
    }

    public void submitDownload() {
        find(By.xpath(POPUP_LOCATOR.concat("//button[contains(@type,'submit')]"))).submit();
    }
}
