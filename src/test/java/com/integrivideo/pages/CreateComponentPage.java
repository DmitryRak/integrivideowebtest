package com.integrivideo.pages;

import com.integrivideo.Component;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

import static com.integrivideo.pages.ComponentTypeEnum.*;

/**
 * Created by asus on 7/13/2017.
 */
public class CreateComponentPage extends PageObject {

    private static final Logger LOGGER = Logger.getLogger(CreateComponentPage.class.getName());
    private final static By CREATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Create')]");
    private final static By UPDATE_COMPONENT_BUTTON_BY = By.xpath("//button[contains(text(), 'Update')]");
    private final static By HIDDEN_PROJECT_ID_BY = By.xpath("//input[contains(@name,'project')]");
    private final static By BACK_TO_PROJECT_BUTTON_BY = By.xpath("//nav[@class='nav']/a[3]");
    private final static By JS_CODE_BY = By.xpath("//code[contains(@class,'component-code')]");
    @FindBy(xpath = "//input[contains(@name,'name')]")
    private WebElement componentNameField;
    @FindBy(xpath = "//*[contains(@name,'type')]")
    private WebElement componentTypeField;

    public void fillInForm(ComponentTypeEnum componentTypeEnum, String name) {
        if (null != componentTypeEnum) {
            Select select = new Select(componentTypeField);
            switch (componentTypeEnum) {
                case VIDEO_CHAT:
                    select.selectByVisibleText(VIDEO_CHAT.toString());
                    break;
                case SINGLE_VIDEO:
                    select.selectByVisibleText(SINGLE_VIDEO.toString());
                    break;
                case MULTI_DEVICE_VIDEO_PLAYER:
                    select.selectByVisibleText(MULTI_DEVICE_VIDEO_PLAYER.toString());
                    break;
                case MULTIPARTY_VIDEO:
                    select.selectByVisibleText(MULTIPARTY_VIDEO.toString());
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

    public void returnToProject() {
        waitABit(500); //without this sleep a click is executed on a wrong element, more sophisticated implementation is required - 12/09/2017
        find(BACK_TO_PROJECT_BUTTON_BY).click();
    }

    public void copyJsCode() {
        find(JS_CODE_BY).click();
    }

    public String getJsCode() {
        return find(JS_CODE_BY).getText().replaceAll("\\r\\n|\\r|\\n", "");
    }

    public Component getComponentDetails() {
        Component component = new Component();
        component.setName(componentNameField.getAttribute("value"));

        waitABit(500); //without this sleep MULTI_DEVICE_VIDEO_PLAYER is received in all cases - 12/09/2017
        String componentTypeText = componentTypeField.getAttribute("value");
        LOGGER.info(componentTypeText);
        if (componentTypeText.equals(VIDEO_CHAT.toString())) {
            component.setComponentTypeEnum(VIDEO_CHAT);
        } else if (componentTypeText.equals(SINGLE_VIDEO.toString())) {
            component.setComponentTypeEnum(SINGLE_VIDEO);
        } else if (componentTypeText.equals(MULTI_DEVICE_VIDEO_PLAYER.toString())) {
            component.setComponentTypeEnum(MULTI_DEVICE_VIDEO_PLAYER);
        } else {
            component.setComponentTypeEnum(MULTIPARTY_VIDEO);
        }
        return component;
    }
}