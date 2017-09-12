package com.integrivideo.stories;

import com.integrivideo.Utils;
import com.integrivideo.steps.CommonSteps;
import com.integrivideo.steps.LandingPageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Pavel Ananich on 11/09/2017.
 */

@Story(LandingStoryTest.class)
@RunWith(SerenityRunner.class)
public class LandingStoryTest extends BaseTest {

    @Steps
    LandingPageSteps landingPageSteps;

    @Steps
    CommonSteps commonSteps;

    @Test
    public void userCanDownloadWhitePaper() {
        Utils.clearDownloadFolder();
        landingPageSteps.downloadWhitePaper("white-paper.pdf");
        commonSteps.validateDownloadedFile("white-paper.pdf");
        Utils.clearDownloadFolder();
    }

    @Test
    public void userCanDownloadBrochure() {
        Utils.clearDownloadFolder();
        landingPageSteps.downloadBrochure("brochure.pdf");
        commonSteps.validateDownloadedFile("brochure.pdf");
        Utils.clearDownloadFolder();
    }
}
