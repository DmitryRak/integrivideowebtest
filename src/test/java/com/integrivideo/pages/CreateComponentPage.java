package com.integrivideo.pages;

import java.util.logging.Logger;

import com.integrivideo.Component;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by asus on 7/13/2017.
 */
public class CreateComponentPage extends PageObject {

    private static final Logger LOGGER = Logger.getLogger(CreateComponentPage.class.getName());
    private final static By CREATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Create')]");
    private final static By UPDATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Update')]");
    private final static By HIDDEN_PROJECT_ID_BY = By.xpath("//input[contains(@name,'project')]");
    @FindBy(xpath = "//input[contains(@name,'name')]")
    private WebElement componentNameField;
    @FindBy(xpath = "//select[contains(@name,'type')]")
    private WebElement componentTypeField;

    public void fillInForm(ComponentTypeEnum componentTypeEnum, String name) {
        if (null != componentTypeEnum) {
            Select select = new Select(componentTypeField);
            switch (componentTypeEnum) {
                case VIDEO_CHAT:
                    select.selectByVisibleText("Video Chat");
                    break;
                case CLOUD_VIDEO_RECORDER:
                    select.selectByVisibleText("Cloud video recorder");
                    break;
                default:
                    break;
            }
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

    public Component getComponentDetails() {
        Component component = new Component();
        component.setName(componentNameField.getAttribute("value"));
        //TODO uncomment after adding of locator
        /*Select select = new Select(componentTypeField);
        String selectedComponent = select.getFirstSelectedOption().getText();
        LOGGER.info(selectedComponent);
        if(selectedComponent.equals(VIDEO_CHAT.toString())){
            component.setComponentTypeEnum(VIDEO_CHAT);
        }else if(selectedComponent.equals(CLOUD_VIDEO_RECORDER.toString())){
            component.setComponentTypeEnum(CLOUD_VIDEO_RECORDER);
        }*/
        return component;
    }
}