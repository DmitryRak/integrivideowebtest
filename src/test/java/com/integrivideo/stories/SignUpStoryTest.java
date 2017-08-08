package com.integrivideo.stories;

import com.integrivideo.Data;
import com.integrivideo.steps.CommonSteps;
import com.integrivideo.steps.LandingPageSteps;
import com.integrivideo.steps.LoginSteps;
import com.integrivideo.steps.SignUpSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
@Story(SignUpStoryTest.class)
@RunWith(SerenityRunner.class)
public class SignUpStoryTest extends BaseTest {

    @Steps
    SignUpSteps signUpSteps;

    @Steps
    LoginSteps loginSteps;

    @Steps
    LandingPageSteps landingPageSteps;

    @Steps
    CommonSteps commonSteps;

    @Test
    public void newUserCanBeRegistered() {
        landingPageSteps.goToSignUpLinkFromFirstBlock();
        signUpSteps.enterCredentialsAndSignUp(Data.RANDOM_EMAIL, Data.USER_1_PASSWORD);
        commonSteps.notificationMessageShouldBeLike("Message with instructions was sent");
        loginSteps.shouldBeOnLoginPage();
    }

    //TODO add test to validate email
    //TODO add test to validate activation
}
