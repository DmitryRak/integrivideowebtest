package com.integrivideo.modals;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserSettingsModal extends PageObject {

    private final By CANCEL_BUTTON_BY = By.xpath("//a[contains(@class, 'integri-user-settings-reset')]");
    private final By SAVE_BUTTON_BY = By.xpath("//button[contains(@class, 'integri-user-settings-save')]");
    @FindBy(xpath = "//input[contains(@name,'userEmail')]")
    private WebElement userEmail;
    @FindBy(xpath = "//input[contains(@name,'userName')]")
    private WebElement userName;
    @FindBy(xpath = "//input[contains(@name,'userPic')]")
    private WebElement userPic;

    public void closeSettingsWindow() {
        waitForRenderedElements(CANCEL_BUTTON_BY);
        clickOn(find(CANCEL_BUTTON_BY));
    }

    public void saveSettings() {
        waitForRenderedElements(SAVE_BUTTON_BY);
        clickOn(find(SAVE_BUTTON_BY));
    }

    public void insertSettings(String name, String email, String userPicUrl) {
        userName.clear();
        userName.sendKeys(name);
        userEmail.clear();
        userEmail.sendKeys(email);
        userPic.clear();
        userPic.sendKeys(userPicUrl);
    }

    public UserSettings getUserSettings() {
        waitForRenderedElements(SAVE_BUTTON_BY);
        UserSettings userSettings = new UserSettings();
        userSettings.setName(userName.getAttribute("value"));
        userSettings.setEmail(userEmail.getAttribute("value"));
        userSettings.setUserPicUrl(userPic.getAttribute("value"));
        return userSettings;
    }
}
