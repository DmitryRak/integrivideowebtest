package com.integrivideo.pages;

import com.integrivideo.Project;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by asus on 7/13/2017.
 */
public class CreateProjectPage extends PageObject {

    private final static By CREATE_PROJECT_BUTTON_BY = By.xpath("//button[contains(text(), 'Create')]");
    private final static By DOMAINS_INPUT_BY = By.xpath("//input[contains(@name,'domains[]')]");
    private final static By REMOVE_DOMAIN_BY = By.xpath("//div[contains(@class,'remove-domain')]");
    private final static By UPDATE_PROJECT_BUTTON_BY = By.xpath("//button[contains(text(), 'Update')]");
    @FindBy(xpath = "//textarea[contains(@name,'description')]")
    private WebElement projectDescriptionField;
    @FindBy(xpath = "//input[contains(@name,'name')]")
    private WebElement projectNameField;

    /**
     * @param name
     * @param description
     * @param domains     - as single string separated by ;
     */
    public void fillInForm(String name, String description, String domains) {
        projectNameField.clear();
        projectNameField.sendKeys(name);
        projectDescriptionField.clear();
        projectDescriptionField.sendKeys(description);
        String[] domainsArray = domains.split(";");
        for (int i = 0; i < domainsArray.length; i++) {
            findAll(DOMAINS_INPUT_BY).get(i).clear();
            findAll(DOMAINS_INPUT_BY).get(i).sendKeys(domainsArray[i]);
        }
    }

    public void clickCreateButton() {
        find(CREATE_PROJECT_BUTTON_BY).submit();
    }

    public void clickUpdateButton() {
        find(UPDATE_PROJECT_BUTTON_BY).submit();
    }

    public void removeDomainByIndex(int domainOrderNum) {
        findAll(REMOVE_DOMAIN_BY).get(domainOrderNum).click();
    }

    public Project getProjectDetails() {
        Project project = new Project();
        project.setName(projectNameField.getText());
        project.setDescription(projectDescriptionField.getText());
        List<WebElementFacade> domains = findAll(DOMAINS_INPUT_BY);

        for (int i = 0; i < domains.size(); i++) {
            project.addDomains(domains.get(i).getText());
        }
        return project;
    }
}
