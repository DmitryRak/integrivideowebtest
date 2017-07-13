package com.integrivideo.steps;

import com.integrivideo.popups.NotificationMessagePopup;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Created by asus on 7/12/2017.
 */
public class CommonSteps extends ScenarioSteps{
    NotificationMessagePopup notificationMessagePopup;

    public void currentPageShouldBe(String url){
        assertThat(getDriver().getCurrentUrl(),equalToIgnoringCase(url));
    }

    public void notificationMessageShouldBeLike(String message){
        assertThat(notificationMessagePopup.getNotificationText(),equalToIgnoringCase(message));
    }
}
