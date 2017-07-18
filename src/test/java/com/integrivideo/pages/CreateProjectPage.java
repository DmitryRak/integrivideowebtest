package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/13/2017.
 */
public class CreateProjectPage extends PageObject {
    @FindBy(xpath="//input[contains(@name,'name')]")
    WebElement projectNameField;

    @FindBy(xpath="//textarea[contains(@name,'description')]")
    WebElement projectDescriptionField;

    private final static By CREATE_PROJECT_BUTTON_BY = By.xpath("//button[contains(text(), 'Create')]");
    private final static By UPDATE_PROJECT_BUTTON_BY = By.xpath("//button[contains(text(), 'Update')]");
    private final static By DOMAINS_INPUT_BY = By.xpath("//input[contains(@name,'domains[]')]");

    /**
     *
     * @param name
     * @param description
     * @param domains - as single string separated by ;
     */
    public void fillInForm(String name, String description, String domains){
        projectNameField.clear();
        projectNameField.sendKeys(name);
        projectDescriptionField.clear();
        projectDescriptionField.sendKeys(description);
        //TODO add ability to input multiple domains
        findAll(DOMAINS_INPUT_BY).get(0).clear();
        findAll(DOMAINS_INPUT_BY).get(0).sendKeys(domains);
    }

    public void clickCreateButton(){
        find(CREATE_PROJECT_BUTTON_BY).submit();
    }

    public void clickUpdateButton(){
        find(UPDATE_PROJECT_BUTTON_BY).submit();
    }
}
