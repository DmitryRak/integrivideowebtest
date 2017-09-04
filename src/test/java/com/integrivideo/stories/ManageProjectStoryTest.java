package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.Project;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
@Story(ManageProjectStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManageProjectStoryTest extends BaseTest {

    @Steps
    ProjectSteps projectSteps;

    @Test
    public void projectCanBeCreated() {
        projectSteps.isOnProjectListPage();
        long projectCount = projectSteps.getProjectCount();
        projectSteps.openCreateProjectPage();
        projectSteps.createProject(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN1);
        projectSteps.shouldBeOnProjectsPage();
        projectSteps.numberOfProjectShouldBeEqualTo(projectCount + 1);
        projectSteps.openProjectPage(projectCount - 1);
        projectSteps.projectDetailsShouldBeLike(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION);
    }

    @Test
    public void projectCanBeEdited() {
        projectSteps.createProject();
        long projectCount = projectSteps.getProjectCount();
        projectSteps.openProjectPage(projectCount - 2);
        Project project = new Project(Data.RANDOM_PROJECT_NAME, Data.RANDOM_PROJECT_DESCRIPTION, Data.RANDOM_PROJECT_DOMAIN);
        projectSteps.editProject(project.getName(), project.getDescription(), project.getDomains().get(0));
        projectSteps.openProjectPage(projectCount - 2);
        projectSteps.projectDetailsShouldBeLike(project.getName(), project.getDescription());
    }

    @Test
    public void projectWithMultipleDomainsCanBeCreated() {
        projectSteps.isOnProjectListPage();
        long projectCount = projectSteps.getProjectCount();
        projectSteps.openCreateProjectPage();
        projectSteps.createProject(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN1 + ";" + Data.RANDOM_PROJECT_DOMAIN);
        projectSteps.shouldBeOnProjectsPage();
        projectSteps.numberOfProjectShouldBeEqualTo(projectCount + 1);
    }

    @Test
    public void domainCanBeRemovedAtProjectCreationPage() {
        projectSteps.isOnProjectListPage();
        long projectCount = projectSteps.getProjectCount();
        projectSteps.openCreateProjectPage();
        projectSteps.fillInForm(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN1 + ";" + Data.RANDOM_PROJECT_DOMAIN);
        projectSteps.removeDomain(1);
        projectSteps.clickOnCreateButton();
        projectSteps.shouldBeOnProjectsPage();
        projectSteps.numberOfProjectShouldBeEqualTo(projectCount + 1);
    }
}
