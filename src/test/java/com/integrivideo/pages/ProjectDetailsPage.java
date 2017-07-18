package com.integrivideo.pages;

import com.integrivideo.Project;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/18/2017.
 */
public class ProjectDetailsPage extends PageObject {
    @FindBy(xpath="//a[contains(text(),'Edit')]")
    WebElement editLink;

    @FindBy(tagName="h1")
    WebElement projectName;

    @FindBy(xpath="//div[contains(@class, 'description')]")
    WebElement projectDescription;

    public Project getProjectDetails(){
        Project project = new Project();
        project.setName(projectName.getText());
        project.setDescription(projectDescription.getText());
        return project;
    }

    public void clickEditProjectLink(){
        editLink.click();
    }
}
