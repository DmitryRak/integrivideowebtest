package com.integrivideo.steps;

import com.integrivideo.pages.CommonPage;
import com.integrivideo.popups.NotificationMessagePopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by asus on 7/12/2017.
 */
public class CommonSteps extends ScenarioSteps{
    public NotificationMessagePopup notificationMessagePopup;
    public CommonPage commonPage;

    public void currentPageShouldBe(String url){
        assertThat(getDriver().getCurrentUrl(),equalToIgnoringCase(url));
    }

    public void notificationMessageShouldBeLike(String message){
        assertThat(notificationMessagePopup.getNotificationText(),containsString(message));
    }

    @Step
    public void doLogout(){
        commonPage.clickLogout();
    }
}
