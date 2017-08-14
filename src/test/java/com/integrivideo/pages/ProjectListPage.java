package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by asus on 7/13/2017.
 */
public class ProjectListPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(ProjectListPage.class.getName());
    public static final By SINGLE_PROJECT_BY = By.xpath("//div[contains(@class, 'project')]");
    @FindBy(xpath = "//div[contains(@class, 'new')]")
    WebElement addProjectButton;

    public void clickAddProject() {
        addProjectButton.click();
    }

    public long getProjectCount() {
        return findAll(SINGLE_PROJECT_BY).size();
    }

    public void openProjectPage(long projectNumberInList) {
        List<WebElementFacade> projects = findAll(SINGLE_PROJECT_BY);
        LOGGER.log(Level.INFO, "Total project count: {0}", projects.size());
        int projectIndex = (int) projectNumberInList;
        LOGGER.log(Level.INFO, "Getting project by index: {0}", projectIndex);
        clickOn(projects.get(projectIndex));
    }
}
