package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.LoginSteps;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
@Story(BasicChatStoryTest.class)
@RunWith(SerenityRunner.class)
public class ManageProjectStoryTest extends BaseTest {

    @Steps
    ProjectSteps projectSteps;

    @Steps
    LoginSteps loginSteps;

    @Test
    public void newProjectCanBeCreated(){
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);

        long projectCount = projectSteps.getProjectCount();
        projectSteps.openCreateProjectPage();
        projectSteps.createProject(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN1);
        projectSteps.shouldBeOnProjectsPage();
        projectSteps.numberOfProjectShouldBeEqualTo(projectCount+1);
    }
}
