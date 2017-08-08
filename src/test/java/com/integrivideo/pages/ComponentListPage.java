package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/13/2017.
 */
public class ComponentListPage extends PageObject {
    public static final By SINGLE_COMPONENT_BY = By.xpath("//div[contains(@class, 'component')]");
    @FindBy(xpath = "//div[contains(@class, 'new')]")
    WebElement addComponentButton;

    public void clickAddComponent() {
        addComponentButton.click();
    }

    public long getComponentCount() {
        return findAll(SINGLE_COMPONENT_BY).size();
    }

    public void openComponentPage(long componentNumberInList) {
        findAll(SINGLE_COMPONENT_BY).get((int) componentNumberInList).click();
    }
}
