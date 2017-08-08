package com.integrivideo.steps;

import com.integrivideo.pages.LandingPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by asus on 7/12/2017.
 */
public class LandingPageSteps extends ScenarioSteps {
    LandingPage landingPage;

    @Step
    public void goToSignUpLinkFromFirstBlock() {
        landingPage.clickSignUpOnFirstBlock();
    }

    @Step
    public void goToLoginPage() {
        landingPage.clickLoginLinkFromTopMenu();
    }
}
