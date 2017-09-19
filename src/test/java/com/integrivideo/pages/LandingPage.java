package com.integrivideo.pages;

import com.integrivideo.Data;
import com.integrivideo.Utils;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
public class LandingPage extends PageObject {

    @FindBy(xpath = "//a[contains(text(), 'Log in')]")
    private WebElement loginLink;
    @FindBy(xpath = "//span[contains(text(), 'Free Sign up')]")
    private WebElement signUpLink;
    @FindBy (xpath = "//button[contains(text(),'White paper')]")
    private WebElement whitePaperButton;
    @FindBy (xpath = "//button[contains(text(),'Brochure')]")
    private WebElement brochureButton;

    public void clickSignUpOnFirstBlock() {
        getDriver().get(Data.BASE_URL);
        clickOn(signUpLink);
    }

    public void clickLoginLinkFromTopMenu() {
        getDriver().get(Data.BASE_URL);
        clickOn(loginLink);
    }

    public void clickDownloadWhitePaper() {
        getDriver().get(Data.BASE_URL);
        Utils.scrollToElement(whitePaperButton);
        whitePaperButton.click();
    }

    public void clickDownloadBrochure() {
        getDriver().get(Data.BASE_URL);
        Utils.scrollToElement(brochureButton);
        brochureButton.click();
    }
}
