package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/13/2017.
 */
public class CreateComponentPage extends PageObject {
    @FindBy(xpath="//input[contains(@name,'name')]")
    WebElement componentNameField;

    private final static By CREATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Create')]");
    private final static By UPDATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Update')]");

    public void fillInForm(ComponentTypeEnum componentTypeEnum, String name){
        componentNameField.clear();
        componentNameField.sendKeys(name);
        //TODO add select type
    }

    public void clickCreateButton(){
        find(CREATE_COMPONENT_BUTTON_BY).submit();
    }

    public void clickUpdateButton(){
        find(UPDATE_COMPONENT_BUTTON_BY).submit();
    }
}