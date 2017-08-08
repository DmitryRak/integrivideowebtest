package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class CommonPage extends PageObject {

    @FindBy(xpath = "//a[contains(text(), 'Projects')]")
    WebElement projectsLink;

    @FindBy(xpath = "//a[contains(text(), 'Billing')]")
    WebElement billingLink;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement logoutLink;

    public void clickLogout() {
        clickOn(logoutLink);
    }

    public void clickProjects() {
        clickOn(projectsLink);
    }

    public void clickBilling() {
        clickOn(billingLink);
    }
}
