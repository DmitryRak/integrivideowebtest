package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.Project;
import com.integrivideo.pages.ComponentTypeEnum;
import com.integrivideo.steps.ComponentSteps;
import com.integrivideo.steps.LoginSteps;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by drak on 7/19/2017.
 */
@Story(ManageProjectStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManageComponentStoryTest extends BaseTest {


    @Steps
    ProjectSteps projectSteps;

    @Steps
    LoginSteps loginSteps;

    @Steps
    ComponentSteps componentSteps;

    @Test
    public void componentCanBeCreated(){
        projectSteps.createProject();
        long projectCount = projectSteps.getProjectCount();
        projectSteps.openProjectPage(projectCount-2);
        componentSteps.openCreateComponentPage();
        componentSteps.createComponent(ComponentTypeEnum.VIDEO_CHAT, "name");
    }
}
