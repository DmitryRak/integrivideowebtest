package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/12/2017.
 */
public class SignUpPage extends PageObject {

    @FindBy(xpath = "//input[contains(@name,'email')]")
    private WebElement emailField;
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginLink;
    @FindBy(xpath = "//input[contains(@name,'password')]")
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(text(),'Sign up')]")
    private WebElement signUpButton;

    public void enterCredentials(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void submitSignUpForm() {
        signUpButton.submit();
    }
}
