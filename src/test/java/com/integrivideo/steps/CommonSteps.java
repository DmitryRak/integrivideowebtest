package com.integrivideo.steps;

import com.integrivideo.pages.CommonPage;
import com.integrivideo.popups.NotificationMessagePopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by asus on 7/12/2017.
 */
public class CommonSteps extends ScenarioSteps {

    private CommonPage commonPage;
    private NotificationMessagePopup notificationMessagePopup;

    public void currentPageShouldBe(String url) {
        assertThat(getDriver().getCurrentUrl()).isEqualToIgnoringCase(url);
    }

    public void notificationMessageShouldBeLike(String message) {
        assertThat(notificationMessagePopup.getNotificationText()).contains(message);
    }

    @Step
    public void doLogout() {
        commonPage.clickLogout();
    }

    @Step
    public void openUrl(String url){
        getDriver().get(url);
    }
}
