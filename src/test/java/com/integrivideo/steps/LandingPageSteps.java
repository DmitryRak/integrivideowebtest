package com.integrivideo.steps;

import com.integrivideo.Data;
import com.integrivideo.pages.LandingPage;
import com.integrivideo.popups.DownloadBrochurePopup;
import com.integrivideo.popups.DownloadWhitePaperPopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Created by asus on 7/12/2017.
 */
public class LandingPageSteps extends ScenarioSteps {

    private LandingPage landingPage;
    private DownloadWhitePaperPopup downloadWhitePaperPopup;
    private DownloadBrochurePopup downloadBrochurePopup;

    @Steps
    CommonSteps commonSteps;

    @Step
    public void goToSignUpLinkFromFirstBlock() {
        landingPage.clickSignUpOnFirstBlock();
    }

    @Step
    public void goToLoginPage() {
        landingPage.clickLoginLinkFromTopMenu();
    }

    @Step
    public void downloadWhitePaper(String filename) {
        landingPage.clickDownloadWhitePaper();
        downloadWhitePaperPopup.enterEmail(Data.USER_2_EMAIL);
        downloadWhitePaperPopup.submitDownload();
        commonSteps.waitForFileToDownload(filename);
    }

    @Step
    public void downloadBrochure(String filename) {
        landingPage.clickDownloadBrochure();
        downloadBrochurePopup.enterEmail(Data.USER_2_EMAIL);
        downloadBrochurePopup.submitDownload();
        commonSteps.waitForFileToDownload(filename);
    }
}
