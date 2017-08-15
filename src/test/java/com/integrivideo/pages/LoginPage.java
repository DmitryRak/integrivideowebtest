package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/12/2017.
 */
public class LoginPage extends PageObject {

    @FindBy(xpath = "//input[contains(@name,'email')]")
    private WebElement emailField;
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;
    @FindBy(xpath = "//input[contains(@name,'password')]")
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(text(),'Sign up')]")
    private WebElement signUpLink;

    public void enterCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void submitLoginForm() {
        loginButton.submit();
    }

}
