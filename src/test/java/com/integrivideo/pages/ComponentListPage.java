package com.integrivideo.pages;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/13/2017.
 */
public class ComponentListPage extends PageObject {

    private static final By SINGLE_COMPONENT_BY = By.xpath("//div[contains(@class, 'component')]");
    private static final Logger LOGGER = Logger.getLogger(ComponentListPage.class.getName());
    @FindBy(xpath = "//div[contains(@class, 'new')]")
    private WebElement addComponentButton;

    public void clickAddComponent() {
        clickOn(addComponentButton);
    }

    public long getComponentCount() {
        return findAll(SINGLE_COMPONENT_BY).size();
    }

    public void openComponentPage(long componentNumberInList) {
        List<WebElementFacade> components = findAll(SINGLE_COMPONENT_BY);
        LOGGER.log(Level.INFO, "Total component count: {0}", components.size());
        int componentIndex = (int) componentNumberInList;
        LOGGER.log(Level.INFO, "Getting component by index: {0}", componentIndex);
        clickOn(components.get(componentIndex));
    }
}
