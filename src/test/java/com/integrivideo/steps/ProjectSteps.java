package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.Project;
import com.integrivideo.pages.CreateProjectPage;
import com.integrivideo.pages.ProjectDetailsPage;
import com.integrivideo.pages.ProjectListPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class ProjectSteps extends ScenarioSteps {

    ProjectListPage projectListPage;

    CreateProjectPage createProjectPage;

    ProjectDetailsPage projectDetailsPage;

    @Steps
    CommonSteps commonSteps;

    @Steps
    LoginSteps loginSteps;

    @Step
    public void shouldBeOnProjectsPage() {
        commonSteps.currentPageShouldBe(Data.PROJECTS_LIST_URL);
    }

    @Step
    public void openCreateProjectPage() {
        projectListPage.clickAddProject();
    }

    @Step
    public void createProject(String name, String description, String domains) {
        if (!getDriver().getCurrentUrl().equals(Data.CREATE_PROJECT_URL)) {
            getDriver().get(Data.CREATE_PROJECT_URL);
        }
        createProjectPage.fillInForm(name, description, domains);
        createProjectPage.clickCreateButton();
        waitABit(2000);
    }

    @Step
    public void createProject() {
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
        createProject(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN1);
    }

    @Step
    public void numberOfProjectShouldBeEqualTo(long number) {
        assertTrue(number == projectListPage.getProjectCount());
    }

    public long getProjectCount() {
        return projectListPage.getProjectCount();
    }

    @Step
    public void openProjectPage(long projectNumberInList) {
        projectListPage.openProjectPage(projectNumberInList);
    }

    @Step
    public void projectDetailsShouldBeLike(String name, String description) {
        Project project = projectDetailsPage.getProjectDetails();
        assertTrue(name.equals(project.getName()));
        assertTrue(description.equals(project.getDescription()));
    }

    @Step
    public void projectDetailsShouldBeLike(Project expectedProject) {
        Project actualProject = createProjectPage.getProjectDetails();
        assertTrue(expectedProject.getName().equals(actualProject.getName()));
        assertTrue(expectedProject.getDescription().equals(actualProject.getDescription()));
        assertTrue(expectedProject.getDomains().equals(actualProject.getDomains()));
    }

    @Step
    public void editProject(String name, String description, String domains) {
        projectDetailsPage.clickEditProjectLink();
        createProjectPage.fillInForm(name, description, domains);
        createProjectPage.clickUpdateButton();
    }

    @Step
    public void fillInForm(String name, String description, String domains) {
        if (!getDriver().getCurrentUrl().equals(Data.CREATE_PROJECT_URL)) {
            getDriver().get(Data.CREATE_PROJECT_URL);
        }
        createProjectPage.fillInForm(name, description, domains);
    }

    @Step
    public void removeDomain(int domainOrderNum) {
        createProjectPage.removeDomainByIndex(domainOrderNum);
    }

    @Step
    public void clickOnCreateButton() {
        createProjectPage.clickCreateButton();
    }
}
