package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.SignUpPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by asus on 7/12/2017.
 */
public class SignUpSteps extends ScenarioSteps {

    private SignUpPage signUpPage;

    @Steps
    private CommonSteps commonSteps;

    @Step
    public void enterCredentialsAndSignUp(String email, String password) {
        signUpPage.enterCredentials(email, password);
        signUpPage.submitSignUpForm();
    }

    @Step
    public void isOnSignUpPage() {
        commonSteps.openUrl(Data.SIGNUP_PAGE_URL);
    }
}
