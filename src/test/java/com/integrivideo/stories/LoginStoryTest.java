package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.CommonSteps;
import com.integrivideo.steps.LandingPageSteps;
import com.integrivideo.steps.LoginSteps;
import com.integrivideo.steps.ProjectSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by asus on 7/13/2017.
 */
@Story(LoginStoryTest.class)
@RunWith(SerenityRunner.class)
public class LoginStoryTest extends BaseTest {

    @Steps
    LoginSteps loginSteps;

    @Steps
    LandingPageSteps landingPageSteps;

    @Steps
    ProjectSteps projectSteps;

    @Test
    public void userCanLoginFromLanding(){
        landingPageSteps.goToLoginPage();
        loginSteps.enterCredentialsAndLogin(Data.USER_2_EMAIL, Data.USER_2_PASSWORD);
        projectSteps.shouldBeOnProjectsPage();
    }
}
