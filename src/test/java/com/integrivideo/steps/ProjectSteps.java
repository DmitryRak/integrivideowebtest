package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.CreateProjectPage;
import com.integrivideo.pages.ProjectListPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static java.util.function.Predicate.isEqual;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class ProjectSteps extends ScenarioSteps {

    ProjectListPage projectListPage;

    CreateProjectPage createProjectPage;

    @Steps
    CommonSteps commonSteps;

    @Step
    public void shouldBeOnProjectsPage(){
        commonSteps.currentPageShouldBe(Data.PROJECTS_LIST_URL);
    }

    @Step
    public void openCreateProjectPage(){
        projectListPage.clickAddProject();
    }

    @Step
    public void createProject(String name, String description, String domains){
        if(!getDriver().getCurrentUrl().equals(Data.CREATE_PROJECT_URL)){
            getDriver().get(Data.CREATE_PROJECT_URL);
        }
        createProjectPage.fillInForm(name, description, domains);
        createProjectPage.clickCreateButton();
    }

    @Step
    public void numberOfProjectShouldBeEqualTo(long number){
        assertTrue(number == projectListPage.getProjectCount());
    }

    public long getProjectCount(){
        return projectListPage.getProjectCount();
    }
}
