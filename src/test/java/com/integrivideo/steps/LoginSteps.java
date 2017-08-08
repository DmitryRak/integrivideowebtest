package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
public class LoginSteps extends ScenarioSteps {

    @Steps
    CommonSteps commonSteps;

    LoginPage loginPage;

    @Step
    public void shouldBeOnLoginPage() {
        commonSteps.currentPageShouldBe(Data.LOGIN_PAGE_URL);
    }

    @Step
    public void enterCredentialsAndLogin(String email, String password) {
        if (!getDriver().getCurrentUrl().equals(Data.LOGIN_PAGE_URL)) {
            getDriver().get(Data.LOGIN_PAGE_URL);
        }
        loginPage.enterCredentials(email, password);
        loginPage.submitLoginForm();
    }
}
