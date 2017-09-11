package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/12/2017.
 */
public class RecoveryPage extends PageObject {

    @FindBy(xpath = "//input[contains(@placeholder,'Email')]")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Recovery password')]")
    private WebElement recoverPasswordButton;

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void submitRecovery() {
        recoverPasswordButton.submit();
    }
}
