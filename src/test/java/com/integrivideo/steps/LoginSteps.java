package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.LoginPage;
import com.integrivideo.pages.RecoveryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitry Rak on 7/12/2017.
 */
public class LoginSteps extends ScenarioSteps {

    @Steps
    private CommonSteps commonSteps;

    private LoginPage loginPage;

    private RecoveryPage recoveryPage;

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

        new WebDriverWait(getDriver(), 10).pollingEvery(3, TimeUnit.SECONDS).
                until(ExpectedConditions.urlToBe(url));
    }

    @Step
    public void goToRecoveryPage() {
        loginPage.clickForgotLink();
    }

    @Step
    public void recoverPassword(String email){
        recoveryPage.enterEmail(email);
        recoveryPage.submitRecovery();
        commonSteps.notificationMessageShouldBeLike("Message with instructions was sent");
    }
}
