package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/13/2017.
 */
public class ProjectListPage extends PageObject {
    @FindBy(xpath="//div[contains(@class, 'new')]")
    WebElement addProjectButton;

    public static final By SINGLE_PROJECT_BY = By.xpath("//div[contains(@class, 'project')]");

    public void clickAddProject(){
        addProjectButton.click();
    }

    public long getProjectCount(){
        return findAll(SINGLE_PROJECT_BY).size();
    }

    public void openProjectPage(long projectNumberInList){
        findAll(SINGLE_PROJECT_BY).get((int)projectNumberInList).click();
    }
}
