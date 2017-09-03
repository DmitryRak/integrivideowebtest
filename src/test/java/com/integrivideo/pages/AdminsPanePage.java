package com.integrivideo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminsPanePage extends PageObject {
    @FindBy(xpath = "//a[contains(text(),'Users')]")
    private WebElement usersButton;

    @FindBy(xpath = "//a[contains(text(),'Projects')]")
    private WebElement projectsButton;

    @FindBy(xpath = "//a[contains(text(),'Components')]")
    private WebElement componentsButton;

    @FindBy(xpath = "//a[contains(text(),'Sessions')]")
    private WebElement sessionsButton;

    @FindBy(xpath = "//a[contains(text(),'Logs')]")
    private WebElement logsButton;

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement logoutLink;

    @FindBy(xpath = "//a[contains(text(), 'Service')]")
    private WebElement serviceLink;

    private static final By USERS_TABLE_BY = By.id("users_report");
    private static final By PROJECTS_TABLE_BY = By.id("projects_report");
    private static final By COMPONENTS_TABLE_BY = By.id("components_report");
    private static final By SESSIONS_TABLE_BY = By.id("sessions_report");
    private static final By LOGS_TABLE_BY = By.id("transactions_report");

    public void openTab(AdminTabEnum adminTabEnum){
        switch (adminTabEnum){
            case LOGS:
                clickOn(logsButton);
                break;
            case USERS:
                clickOn(usersButton);
                break;
            case PROJECTS:
                clickOn(projectsButton);
                break;
            case SESSIONS:
                clickOn(sessionsButton);
                break;
            case COMPONENTS:
                clickOn(componentsButton);
                break;
        }

    }

    public boolean isAdminTableVisible(AdminTabEnum adminTabEnum){
        int numOfElementsOnPage = 0;
        switch (adminTabEnum){
            case LOGS:
                numOfElementsOnPage = findAll(LOGS_TABLE_BY).size();
                break;
            case USERS:
                numOfElementsOnPage = findAll(USERS_TABLE_BY).size();
                break;
            case PROJECTS:
                numOfElementsOnPage = findAll(PROJECTS_TABLE_BY).size();
                break;
            case SESSIONS:
                numOfElementsOnPage = findAll(SESSIONS_TABLE_BY).size();
                break;
            case COMPONENTS:
                numOfElementsOnPage = findAll(COMPONENTS_TABLE_BY).size();
                break;
        }
        return numOfElementsOnPage > 0;
    }

    public void clickOnServiceLink(){
        clickOn(serviceLink);
    }
}
