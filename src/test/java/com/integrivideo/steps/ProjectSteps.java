package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.Project;
import com.integrivideo.pages.CreateProjectPage;
import com.integrivideo.pages.ProjectDetailsPage;
import com.integrivideo.pages.ProjectListPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class ProjectSteps extends ScenarioSteps {

    private CreateProjectPage createProjectPage;
    private ProjectDetailsPage projectDetailsPage;
    private ProjectListPage projectListPage;
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private CommonSteps commonSteps;

    @Step
    public void isOnProjectListPage(){
        loginSteps.opensUrlAndLogin(Data.PROJECTS_LIST_URL, Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
    }

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
        createProjectPage.fillInForm(name, description, domains);
        createProjectPage.clickCreateButton();
        waitABit(2000);
    }

    @Step
    public void createProject() {
        loginSteps.opensUrlAndLogin(Data.CREATE_PROJECT_URL, Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
        createProject(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN1);
    }

    @Step
    public void numberOfProjectShouldBeEqualTo(final long number) {
        assertThat(projectListPage.getProjectCount()).isEqualTo(number);
    }

    public long getProjectCount() {
        return projectListPage.getProjectCount();
    }

    @Step
    public void openProjectPage(long projectNumberInList) {
        projectListPage.openProjectPage(projectNumberInList);
    }

    @Step
    public void projectDetailsShouldBeLike(final String name, final String description) {
        Project project = projectDetailsPage.getProjectDetails();
        assertThat(project.getName()).isEqualTo(name);
        assertThat(project.getDescription()).isEqualTo(description);
    }

    @Step
    public void projectDetailsShouldBeLike(Project expectedProject) {
        Project actualProject = createProjectPage.getProjectDetails();
        assertThat(actualProject).isEqualTo(expectedProject);
    }

    @Step
    public void projectPricesShouldBeLike(int base, int usage, int total) {
        assertThat(projectDetailsPage.getProjectPrices()).
                isEqualTo(String.format("BASE - $%s | USAGE - $%s | TOTAL - $%s", base, usage, total));
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
