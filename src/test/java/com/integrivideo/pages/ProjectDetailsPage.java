package com.integrivideo.pages;

import com.integrivideo.Project;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 7/18/2017.
 */
public class ProjectDetailsPage extends PageObject {

    @FindBy(xpath = "//a[contains(text(),'Edit')]")
    private WebElement editLink;
    @FindBy(xpath = "//div[contains(@class, 'description')]")
    private WebElement projectDescription;
    @FindBy(tagName = "h1")
    private WebElement projectName;
    @FindBy(xpath = "//h3[starts-with(.,'Base')]")
    private WebElement prices;

    public Project getProjectDetails() {
        Project project = new Project();
        project.setName(projectName.getText());
        project.setDescription(projectDescription.getText());
        return project;
    }

    public String getProjectPrices() {
        waitFor(prices);
        return prices.getText();
    }

    public void clickEditProjectLink() {
        editLink.click();
    }
}
