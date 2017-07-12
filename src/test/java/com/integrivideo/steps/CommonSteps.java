package com.integrivideo.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertTrue;

/**
 * Created by asus on 7/12/2017.
 */
public class CommonSteps extends ScenarioSteps{

    public void currentPageShouldBe(String url){
        assertThat(getDriver().getCurrentUrl(),equalToIgnoringCase(url));
    }
}
