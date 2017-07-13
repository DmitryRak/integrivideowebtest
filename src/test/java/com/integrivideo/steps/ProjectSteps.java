package com.integrivideo.steps;

import com.integrivideo.Data;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by Dmitry Rak on 7/13/2017.
 */
public class ProjectSteps extends ScenarioSteps {


    @Steps
    CommonSteps commonSteps;

    @Step
    public void shouldBeOnProjectsPage(){
        commonSteps.currentPageShouldBe(Data.PROJECTS_PAGE_URL);
    }
}
