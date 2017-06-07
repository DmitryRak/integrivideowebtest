package com.integrivideo.pages;

import com.integrivideo.Message;
import com.integrivideo.elements.WebElementHelper;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.pages.WebElementFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Rak on 4/15/2017.
 */
public class Chat extends PageObject{
    @FindBy(xpath = "//integri-div[contains(@class, 'integri-chat-input')]//textarea")
    private WebElement textInput;

    public void inputText(String text){
        textInput.sendKeys(text);
    }
}
