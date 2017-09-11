package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.LandingPage;
import com.integrivideo.popups.DownloadWhitePaperPopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by asus on 7/12/2017.
 */
public class LandingPageSteps extends ScenarioSteps {

    private LandingPage landingPage;
    private DownloadWhitePaperPopup downloadWhitePaperPopup;

    @Step
    public void goToSignUpLinkFromFirstBlock() {
        landingPage.clickSignUpOnFirstBlock();
    }

    @Step
    public void goToLoginPage() {
        landingPage.clickLoginLinkFromTopMenu();
    }

    @Step
    public void downloadWhitePaper() {
        landingPage.clickDownloadWhitePaper();
        downloadWhitePaperPopup.enterEmail(Data.USER_2_EMAIL);
        downloadWhitePaperPopup.submitDownload();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }

    }
}
