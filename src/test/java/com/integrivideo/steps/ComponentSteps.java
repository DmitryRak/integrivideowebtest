package com.integrivideo.steps;

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

    @Step
    public void openCreateComponentPage() {
        componentListPage.clickAddComponent();
    }

    @Step
    public void createComponent(ComponentTypeEnum componentType, String name) {
        createComponentPage.fillInForm(componentType, name);
        createComponentPage.clickCreateButton();
    }

    @Step
    public void numberOfComponentsShouldBeEqualTo(long number) {
        assertTrue(number == componentListPage.getComponentCount());
    }

    public long getComponentCount() {
        return componentListPage.getComponentCount();
    }

    public void openComponentPage(long componentNumberInList) {
        componentListPage.openComponentPage(componentNumberInList);
    }

    //TODO
    public void componentDetailsShouldBeLike(ComponentTypeEnum componentTypeEnum, String name) {
        Project project = projectDetailsPage.getProjectDetails();
        assertTrue(name.equals(project.getName()));
    }

    //TODO
    public void editComponent(String name) {
        createComponentPage.clickUpdateButton();
    }
}
