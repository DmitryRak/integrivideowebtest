package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class CommonPage extends PageObject {

    @FindBy(xpath = "//a[contains(text(), 'Billing')]")
    private WebElement billingLink;
    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement logoutLink;
    @FindBy(xpath = "//a[contains(text(), 'Projects')]")
    private WebElement projectsLink;

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
