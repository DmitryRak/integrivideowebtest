package com.integrivideo.pages;

import com.integrivideo.Data;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/12/2017.
 */
public class LandingPage extends PageObject {
    @FindBy(xpath="//span[contains(text(), 'Free Sign up')]")
    WebElement signUpLink;


    public void clickSignUpOnFirstBlock(){
        getDriver().get(Data.BASE_URL);
        signUpLink.click();
    }
}
