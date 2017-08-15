package com.integrivideo.steps;

import java.util.concurrent.TimeUnit;

import com.integrivideo.Data;
import com.integrivideo.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Step(callNestedMethods = false)
    public void opensUrlAndLogin(String url, String email, String password) {
        getDriver().get(url);
        if (getDriver().getCurrentUrl().equals(Data.LOGIN_PAGE_URL)) {
            loginPage.enterCredentials(email, password);
            loginPage.submitLoginForm();
        }

        new WebDriverWait(getDriver(), 10).pollingEvery(3, TimeUnit.SECONDS).until(ExpectedConditions.urlToBe(url));
    }
}
