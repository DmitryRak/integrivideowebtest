package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.Project;
import com.integrivideo.pages.ComponentListPage;
import com.integrivideo.pages.ComponentTypeEnum;
import com.integrivideo.pages.CreateComponentPage;
import com.integrivideo.pages.ProjectDetailsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class ComponentSteps extends ScenarioSteps {

    ComponentListPage componentListPage;

    CreateComponentPage createComponentPage;

    ProjectDetailsPage projectDetailsPage;

    @Steps
    CommonSteps commonSteps;

    @Steps
    LoginSteps loginSteps;

    @Step
    public void openCreateComponentPage(){
        componentListPage.clickAddComponent();
    }

    @Step
    public void createComponent(ComponentTypeEnum componentType, String name){
        createComponentPage.fillInForm(componentType, name);
        createComponentPage.clickCreateButton();
    }

    @Step
    public void numberOfComponentsShouldBeEqualTo(long number){
        assertTrue(number == componentListPage.getComponentCount());
    }

    public long getComponentCount(){
        return componentListPage.getComponentCount();
    }

    public void openComponentPage(long projectNumberInList){
        componentListPage.openComponentPage(projectNumberInList);
    }

    //TODO
    public void projectDetailsShouldBeLike(String name, String description){
        Project project = projectDetailsPage.getProjectDetails();
        assertTrue(name.equals(project.getName()));
        assertTrue(description.equals(project.getDescription()));
    }

    //TODO
    public void editProject(String name, String description, String domains){
        projectDetailsPage.clickEditProjectLink();
        //createComponentPage.fillInForm(name, description, domains);
        createComponentPage.clickUpdateButton();
    }
}
