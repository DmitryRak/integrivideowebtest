package com.integrivideo.steps;

import com.integrivideo.Data;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by asus on 7/12/2017.
 */
public class LoginSteps extends ScenarioSteps {

    @Steps
    CommonSteps commonSteps;

    @Step
    public void shouldBeOnLoginPage(){
        commonSteps.currentPageShouldBe(Data.LOGIN_PAGE_URL);
    }
}
