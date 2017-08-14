package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by asus on 7/13/2017.
 */
public class CreateComponentPage extends PageObject {
    private final static By CREATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Create')]");
    private final static By UPDATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Update')]");
    @FindBy(xpath = "//input[contains(@name,'name')]")
    WebElement componentNameField;
    @FindBy(xpath = "//select[contains(@name,'type')]")
    WebElement componentTypeField;

    public void fillInForm(ComponentTypeEnum componentTypeEnum, String name) {
        Select select = new Select(componentTypeField);
        switch(componentTypeEnum){
            case VIDEO_CHAT:
                select.selectByVisibleText("Video Chat");
                break;
            case CLOUD_VIDEO_RECORDER:
                select.selectByVisibleText("Cloud video recorder");
                break;
            default:
                break;
        }
        componentNameField.clear();
        componentNameField.sendKeys(name);
    }

    public void clickCreateButton() {
        find(CREATE_COMPONENT_BUTTON_BY).submit();
    }

    public void clickUpdateButton() {
        find(UPDATE_COMPONENT_BUTTON_BY).submit();
    }
}