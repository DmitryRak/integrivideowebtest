package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/18/2017.
 */
public class ProjectNotFoundPage extends PageObject {

    @FindBy(xpath = "//h1")
    private WebElement errorDescription;

    public String getErrorDescription() {
        return errorDescription.getText();
    }
}
