package com.integrivideo.stories;

import com.integrivideo.pages.ComponentTypeEnum;
import com.integrivideo.steps.ComponentSteps;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by drak on 7/19/2017.
 */
@Story(ManageComponentStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManageComponentStoryTest extends BaseTest {


    @Steps
    ComponentSteps componentSteps;
    @Steps
    ProjectSteps projectSteps;

    @Test
    public void videoChatComponentCanBeCreated() {
        projectSteps.createProject();
        projectSteps.openProjectPage(projectSteps.getProjectCount() - 2);
        componentSteps.openCreateComponentPage();
        componentSteps.createComponent(ComponentTypeEnum.VIDEO_CHAT, "name");
        //TODO add steps to validate content
    }

    @Test
    public void cloudVideoRecorderComponentCanBeCreated() {
        projectSteps.createProject();
        projectSteps.openProjectPage(projectSteps.getProjectCount() - 2);
        componentSteps.openCreateComponentPage();
        componentSteps.createComponent(ComponentTypeEnum.CLOUD_VIDEO_RECORDER, "name");
        //TODO add steps to validate content
    }

    @Test
    public void componentCanBeUpdated() {
        projectSteps.createProject();
        projectSteps.openProjectPage(projectSteps.getProjectCount() - 2);
        componentSteps.openCreateComponentPage();
        componentSteps.createComponent(ComponentTypeEnum.CLOUD_VIDEO_RECORDER, "name");
        componentSteps.editComponent("edited");
        //TODO add steps to validate content
    }
}
